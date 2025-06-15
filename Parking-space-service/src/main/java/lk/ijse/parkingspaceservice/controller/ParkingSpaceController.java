package lk.ijse.parkingspaceservice.controller;

import lk.ijse.parkingspaceservice.model.ParkingSpace;
import lk.ijse.parkingspaceservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
