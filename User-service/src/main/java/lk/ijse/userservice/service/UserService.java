package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.model.BookingHistory;
import lk.ijse.userservice.model.User;
import lk.ijse.userservice.repo.BookingHistoryRepo;
import lk.ijse.userservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookingHistoryRepo bookingHistoryRepo;


    public UserDTO getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return new UserDTO(user.getUserId(), user.getName(), user.getEmail(),user.getNic(), user.getPassword(), user.getAddress(), user.getPhone(), user.getRole());
    }


    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();

        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setNic(userDTO.getNic());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setRole(userDTO.getRole());

        userRepo.save(user);

        return userDTO;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public UserDTO loginUser(String email, String password) {
        List<User> users = userRepo.findByEmailAndPassword(email, password);

        if (users.isEmpty()) {
            throw new RuntimeException("Invalid login credentials");
        } else if (users.size() > 1) {
            throw new RuntimeException("Duplicate user accounts found");
        }


        User user = users.get(0);
        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getNic(),
                user.getPassword(),
                user.getAddress(),
                user.getPhone(),
                user.getRole()
        );
    }
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setNic(userDTO.getNic());
        existingUser.setPassword(userDTO.getPassword()); // hash if needed
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setRole(userDTO.getRole());

        User updatedUser = userRepo.save(existingUser);

        return new UserDTO(
                updatedUser.getUserId(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getNic(),
                updatedUser.getPassword(),
                updatedUser.getAddress(),
                updatedUser.getPhone(),
                updatedUser.getRole()
        );
    }
    public List<BookingHistory> getHistory(Long userId) {
        return bookingHistoryRepo.findByUserId(userId);
    }

    public BookingHistory logBooking(BookingHistory history) {
        return bookingHistoryRepo.save(history);
    }

}
