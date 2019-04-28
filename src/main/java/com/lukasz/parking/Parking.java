package com.lukasz.parking;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lukasz.employee.Employee;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "PARKING")
public class Parking {
    @Id
    @GeneratedValue
    private Integer parkingId;

    @Column
    private String name;


    //TODO JAK TO ODKOMENTUJE TO WYRZUCA BLAD 415, moze JsonIgrorE?
    //@JsonManagedReference(value = "blad w parking")
    //@OneToMany(mappedBy = "parking")
    //private List<Employee> employees;

//TODO JEDYNE CO NIE DZIALA TERAZ TO TO ZE POST NA PARKINGU DODAJE ZAWSZE PUSTEGO OBIEKTA


/*
    @OneToMany(mappedBy = "parking")
    private List<Client> clients;

    @OneToMany(mappedBy = "parking")
    private List<Bill> bills;
*/


    public Parking() {
    }


    public Parking(Integer parkingId, String name) {
        this.parkingId = parkingId;
        this.name = name;
    }

    public Parking(Parking parking) {

    }

    @Override
    public String toString() {
        return "ParkingEntity{" +
                "id=" + parkingId +
                ", name='" + name + '\'' +
                '}';
    }
}
