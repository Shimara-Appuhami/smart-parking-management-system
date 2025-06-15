package lk.ijse.parkingspaceservice.repo;

import lk.ijse.parkingspaceservice.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceRepo extends JpaRepository<ParkingSpace,Long> {
    List<ParkingSpace>findByLocation(String location);
    List<ParkingSpace>findByStatus(String status);
}
