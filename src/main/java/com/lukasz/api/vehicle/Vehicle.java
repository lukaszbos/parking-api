package com.lukasz.api.vehicle;

import com.lukasz.api.carpark.CarPark;
import com.lukasz.api.client.Client;
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
public class Vehicle {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long vehicleId;
    private String numberPlate;
    private String brand;
    private String color;

    @JoinColumn(name = "clientId")
    @ManyToOne
    private Client client;

}