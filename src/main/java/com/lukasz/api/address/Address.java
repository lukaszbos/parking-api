package com.lukasz.api.address;

import com.lukasz.api.carpark.CarPark;
import com.lukasz.api.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long addressId;
    String city;
    String street;
    int houseNumber;
    double latitude;
    double longitude;

    //@OneToOne(mappedBy = "address")
    //private CarPark carPark;

    public Address(String city, String street, int houseNumber, double latitude, double longitude) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}