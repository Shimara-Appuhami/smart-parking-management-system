package lk.ijse.parkingspaceservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingId;
    private String location;
    private String zone;
    private String status;//available or occupied or reserved
    @Column(nullable = false)
    private boolean isReserved=true;

}
