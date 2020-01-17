package com.lukasz.api.bill;

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
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bill {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long billId;
    private Integer numberOfHours;
    private BigDecimal cost;

    @JoinColumn(name = "clientId")
    @ManyToOne
    private Client client;

    @ManyToOne
    @JoinColumn(name = "carParkId")
    private CarPark carPark;

    public Bill(Integer numberOfHours, BigDecimal cost, Client client, CarPark carPark) {
        this.numberOfHours = numberOfHours;
        this.cost = cost;
        this.client = client;
        this.carPark = carPark;
    }

    public Bill(Integer numberOfHours, BigDecimal cost, Client client) {
        this.numberOfHours = numberOfHours;
        this.cost = cost;
        this.client = client;
    }

}