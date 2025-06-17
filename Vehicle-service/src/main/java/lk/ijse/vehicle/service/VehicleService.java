package lk.ijse.vehicle.service;

import lk.ijse.vehicle.dto.UserDTO;
import lk.ijse.vehicle.dto.VehicleUserDTO;
import lk.ijse.vehicle.model.Vehicle;
import lk.ijse.vehicle.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private RestTemplate restTemplate;

    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle existing = vehicleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        existing.setVehicleNumber(updatedVehicle.getVehicleNumber());
        existing.setVehicleType(updatedVehicle.getVehicleType());
        existing.setVehicleColor(updatedVehicle.getVehicleColor());
        existing.setUserId(updatedVehicle.getUserId());
        return vehicleRepo.save(existing);
    }

    public Vehicle getVehicle(Long id) {
        return vehicleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    public VehicleUserDTO getVehicleWithUser(Long vehicleId) {
        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (vehicle.getUserId() == null) {
            throw new RuntimeException("User ID is null for this vehicle");
        }

        String userServiceUrl = "http://USER-SERVICE/api/v1/user/" + vehicle.getUserId();
        UserDTO userDTO = restTemplate.getForObject(userServiceUrl, UserDTO.class);

        VehicleUserDTO dto = new VehicleUserDTO();
        dto.setVehicle(vehicle);
        dto.setUser(userDTO);
        return dto;
    }


        public String simulateEntry(Long vehicleId) {
            Vehicle vehicle = vehicleRepo.findById(vehicleId)
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));

            vehicle.setEntered(true);
            vehicleRepo.save(vehicle);
            return vehicle.getVehicleNumber()+"Vehicle entered successfully";
        }


public String simulateExit(Long vehicleId) {
    Vehicle vehicle = vehicleRepo.findById(vehicleId)
            .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    vehicle.setEntered(false);
    vehicleRepo.save(vehicle);
    return vehicle.getVehicleNumber()+"Vehicle exited successfully";
}
}
