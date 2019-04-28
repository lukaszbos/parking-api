package com.lukasz.bill;

import com.lukasz.parking.Parking;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BILL")
public class Bill {
    @GeneratedValue
    @Id
    private Integer billId;

    @Column
    private Integer numberOfHours;

    @Column
    private Integer cost;

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