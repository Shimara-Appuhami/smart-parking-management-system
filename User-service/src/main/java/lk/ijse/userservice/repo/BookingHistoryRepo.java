package lk.ijse.userservice.repo;

import lk.ijse.userservice.model.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingHistoryRepo extends JpaRepository<BookingHistory,Long> {
    List<BookingHistory> findByUserId(Long userId);

}
