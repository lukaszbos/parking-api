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

    @Override
    public String toString() {
        return "ParkingEntity{" +
                "id=" + parkingId +
                ", name='" + name + '\'' +
                '}';
    }
}
