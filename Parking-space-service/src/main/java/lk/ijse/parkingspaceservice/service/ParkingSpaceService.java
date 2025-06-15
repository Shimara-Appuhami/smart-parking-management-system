package lk.ijse.parkingspaceservice.service;

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

    public ParkingSpace saveParking(ParkingSpace parkingSpace) {
        return parkingSpaceRepo.save(parkingSpace);
    }
}
