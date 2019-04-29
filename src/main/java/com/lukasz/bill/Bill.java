package com.lukasz.bill;

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
    private Integer billId;
    private Integer numberOfHours;
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "parkingId")
    private Parking parking;

    @Override
    public String toString() {
        return "BillEntity{" +
                "billId=" + billId +
                ", numberOfHours='" + numberOfHours + '\'' +
                ", cost='" + cost + '\'' +
                ", parking='" + parking + '\'' +
                '}';
    }
}