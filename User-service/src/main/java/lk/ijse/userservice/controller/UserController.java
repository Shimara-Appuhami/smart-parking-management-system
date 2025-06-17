package lk.ijse.userservice.controller;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.model.BookingHistory;
import lk.ijse.userservice.model.User;
import lk.ijse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/save")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    //get All
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getParking() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO loginRequest) {

        UserDTO response = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{id}/history")
    public ResponseEntity<List<BookingHistory>> getHistory(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getHistory(id));
    }

    @PostMapping("/log")
    public ResponseEntity<BookingHistory> log(@RequestBody BookingHistory history) {
        return ResponseEntity.ok(userService.logBooking(history));
    }






}
