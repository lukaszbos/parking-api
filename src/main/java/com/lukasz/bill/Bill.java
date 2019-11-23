package com.lukasz.bill;

import com.lukasz.client.Client;
import com.lukasz.parking.Parking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BILL")
public class Bill {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long billId;
    private Integer numberOfHours;
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "parkingId")
    private Parking parking;

    @JoinColumn(name = "clientId")
    @ManyToOne
    private Client client;

}