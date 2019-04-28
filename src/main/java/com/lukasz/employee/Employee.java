package com.lukasz.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lukasz.parking.Parking;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @GeneratedValue
    @Id
    private Integer employeeId;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String position;

   // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JsonBackReference(value = "blad w employe")
    @JoinColumn(name = "parkingId")
    @ManyToOne
    private Parking parking;

    public Employee() {

    }

    public Employee(Integer employeeId, String name, String surname, String position, Parking parking) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.parking = parking;
    }

    @Override
    public String toString() {
        return "EmploeeEntity{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                ", parking='" + parking + '\'' +
                '}';
    }
}