package lk.ijse.userservice.repo;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    UserDTO findByUserId(Long id);
    List<User> findByEmailAndPassword(String email, String password);
    List<User> findByEmail(String email);
    List<User> findByNic(String nic);

}
