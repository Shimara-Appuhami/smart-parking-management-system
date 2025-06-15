package lk.ijse.parkingspaceservice.controller;

import jakarta.persistence.EntityNotFoundException;
import lk.ijse.parkingspaceservice.model.ParkingSpace;
import lk.ijse.parkingspaceservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingSpaceController {
    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ParkingSpace>> getParking() {
        List<ParkingSpace> parkingSpaces = parkingSpaceService.getAllParking();
        return new ResponseEntity<>(parkingSpaces, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<ParkingSpace> createParkingSpace(@RequestBody ParkingSpace parkingSpace) {
        ParkingSpace savedSpace = parkingSpaceService.saveParking(parkingSpace);
        return new ResponseEntity<>(savedSpace, HttpStatus.CREATED);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ParkingSpace>> getByStatus(@PathVariable String status) {
        List<ParkingSpace> parkingSpace = parkingSpaceService.searchByStatus(status);
        return new ResponseEntity<>(parkingSpace, HttpStatus.OK);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<ParkingSpace>> getByLocation(@PathVariable String location) {
        List<ParkingSpace> parkingSpace = parkingSpaceService.searchByLocation(location);
        return new ResponseEntity<>(parkingSpace, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpace> updateSpot(@PathVariable Long id, @RequestBody ParkingSpace updatedSpot) {
        ParkingSpace parkingSpace = parkingSpaceService.updateSpot(id, updatedSpot);
        return ResponseEntity.ok(parkingSpace);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteParkingSpot(@PathVariable Long id) {
        try {
            parkingSpaceService.deleteSpot(id);
            return ResponseEntity.ok("Deleted parking spot with ID: " + id);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<ParkingSpace> reserveOrReleaseSpot(
            @PathVariable Long id,
            @RequestParam boolean reserve) {
        ParkingSpace updatedSpot = parkingSpaceService.toggleReservation(id, reserve);
        return ResponseEntity.ok(updatedSpot);
    }

}
