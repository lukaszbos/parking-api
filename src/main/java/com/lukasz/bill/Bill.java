package com.lukasz.bill;

import com.lukasz.client.Client;
import com.lukasz.parking.Parking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Bill")
@Table (name="Bill")
public class Bill {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long billId;
    private Integer numberOfHours;
    private BigDecimal cost;
//    private LocalDateTime enteredParkingAt;
  //  private LocalDateTime leftParkingAt;

    //TODO przerzucic parking do TICKET
    @ManyToOne
    @JoinColumn(name = "parkingId")
    private Parking parking;

    @JoinColumn(name = "clientId")
    @ManyToOne
    private Client client;

    public Bill(Integer numberOfHours, BigDecimal cost, Parking parking, Client client) {

        this.numberOfHours = numberOfHours; //TODO o to mi potrzebne?
        this.cost = cost;
        this.parking = parking;
        this.client = client;
    }

}