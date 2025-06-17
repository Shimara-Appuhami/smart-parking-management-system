package lk.ijse.vehicle.repo;

import lk.ijse.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepo extends JpaRepository<Vehicle,Long> {
    Optional<Vehicle>findByVehicleId(Long vehicleId);
    List<Vehicle> findByUserId(Long userId);

}
