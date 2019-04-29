package com.lukasz.parking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PARKING")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parkingId;

    @Column
    private String name;

    @Override
    public String toString() {
        return "ParkingEntity{" +
                "id=" + parkingId +
                ", name='" + name + '\'' +
                '}';
    }
}
