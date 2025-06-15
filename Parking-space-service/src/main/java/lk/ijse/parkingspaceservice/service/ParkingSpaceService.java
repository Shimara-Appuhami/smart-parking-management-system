package lk.ijse.parkingspaceservice.service;

import jakarta.persistence.EntityNotFoundException;
import lk.ijse.parkingspaceservice.model.ParkingSpace;
import lk.ijse.parkingspaceservice.repo.ParkingSpaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepo parkingSpaceRepo;

    public List<ParkingSpace> getAllParking() {
        return parkingSpaceRepo.findAll();
    }

    public List<ParkingSpace>searchByStatus(String status) {
        return parkingSpaceRepo.findByStatus(status);
    }
    public List<ParkingSpace>searchByLocation(String location) {
        return parkingSpaceRepo.findByLocation(location);
    }

    public ParkingSpace saveParking(ParkingSpace parkingSpace) {
        return parkingSpaceRepo.save(parkingSpace);
    }

    public ParkingSpace updateSpot(Long parkingId, ParkingSpace newSpot) {
        ParkingSpace existingParkingSpace = parkingSpaceRepo.findById(parkingId).orElseThrow(()-> new RuntimeException("Spot not found"));

        existingParkingSpace.setReserved(newSpot.isReserved());
        existingParkingSpace.setLocation(newSpot.getLocation());
        existingParkingSpace.setStatus(newSpot.getStatus());
        existingParkingSpace.setZone(newSpot.getZone());
        return parkingSpaceRepo.save(existingParkingSpace);
    }

    public ParkingSpace deleteSpot(Long parkingId) {
        ParkingSpace parkingSpace = parkingSpaceRepo.findById(parkingId).orElse(null);
        if (parkingSpace != null) {
            parkingSpaceRepo.delete(parkingSpace);
        }
        return parkingSpace;
    }
    public ParkingSpace toggleReservation(Long parkingId, boolean reserve) {
        ParkingSpace parkingSpace = parkingSpaceRepo.findById(parkingId)
                .orElseThrow(() -> new EntityNotFoundException("Parking spot not found: " + parkingId));

        if (reserve) {
            parkingSpace.setReserved(true);
            parkingSpace.setStatus("reserved");
        } else {
            parkingSpace.setReserved(false);
            parkingSpace.setStatus("available");
        }

        return parkingSpaceRepo.save(parkingSpace);
    }


}
