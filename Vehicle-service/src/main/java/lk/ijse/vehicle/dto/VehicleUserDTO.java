package lk.ijse.vehicle.dto;

import lk.ijse.vehicle.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class VehicleUserDTO {
    private Vehicle vehicle;
    private UserDTO user;

    public VehicleUserDTO() {
    }

    public VehicleUserDTO(Vehicle vehicle, UserDTO user) {
        this.vehicle = vehicle;
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
