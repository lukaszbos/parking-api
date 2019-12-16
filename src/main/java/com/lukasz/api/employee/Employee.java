package com.lukasz.api.employee;

import com.lukasz.api.parking.Parking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long employeeId;
    private String name;
    private String surname;
    private String position;

    @JoinColumn(name = "parkingId")
    @ManyToOne
    private Parking parking;

    public Employee(String name, String surname, String position, Parking parking) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.parking = parking;
    }
}