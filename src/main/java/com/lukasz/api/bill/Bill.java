package com.lukasz.api.bill;

import com.lukasz.api.client.Client;
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
    @JoinColumn(name = "parkingId")
    private Parking parking;

    public Bill(Integer numberOfHours, BigDecimal cost, Client client, Parking parking) {
        this.numberOfHours = numberOfHours;
        this.cost = cost;
        this.client = client;
        this.parking = parking;
    }

    public Bill(Integer numberOfHours, BigDecimal cost, Client client) {
        this.numberOfHours = numberOfHours;
        this.cost = cost;
        this.client = client;
    }

}