package lk.ijse.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class BookingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long userId;
    private String vehicleNumber;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String location;

    public BookingHistory() {
    }

    public BookingHistory(Long bookingId, Long userId, String vehicleNumber, LocalDateTime entryTime, LocalDateTime exitTime, String location) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.vehicleNumber = vehicleNumber;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.location = location;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
