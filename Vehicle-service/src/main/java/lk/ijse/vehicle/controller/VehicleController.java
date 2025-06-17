package lk.ijse.vehicle.controller;

import lk.ijse.vehicle.dto.UserDTO;
import lk.ijse.vehicle.dto.VehicleUserDTO;
import lk.ijse.vehicle.model.Vehicle;
import lk.ijse.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;


        @PostMapping("/save")
        public ResponseEntity<Vehicle> register(@RequestBody Vehicle vehicle) {
            return ResponseEntity.ok(vehicleService.registerVehicle(vehicle));
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle vehicle) {
            return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicle));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Vehicle> get(@PathVariable Long id) {
            return ResponseEntity.ok(vehicleService.getVehicle(id));
        }

        @GetMapping("/getAll")
        public ResponseEntity<List<Vehicle>> getAll() {
            return ResponseEntity.ok(vehicleService.getAllVehicles());
        }

        @GetMapping("/with-user/{id}")
        public ResponseEntity<VehicleUserDTO> getWithUser(@PathVariable Long id) {
            return ResponseEntity.ok(vehicleService.getVehicleWithUser(id));
        }

        @PutMapping("/{id}/enter")
        public ResponseEntity<String> enter(@PathVariable Long id) {
            return ResponseEntity.ok(vehicleService.simulateEntry(id));
        }

        @PutMapping("/{id}/exit")
        public ResponseEntity<String> exit(@PathVariable Long id) {
            return ResponseEntity.ok(vehicleService.simulateExit(id));
        }


}
