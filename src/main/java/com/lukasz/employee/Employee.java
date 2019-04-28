package com.lukasz.employee;

import com.lukasz.parking.Parking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @JoinColumn(name = "parkingId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Parking parking;


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