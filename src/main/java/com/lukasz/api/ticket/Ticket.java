package com.lukasz.api.ticket;

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
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long ticketId;
    private LocalDateTime enteredParkingAt;
    private boolean isClientAtParking;
    private LocalDateTime leftParkingAt;

    @JoinColumn(name = "clientId")
    @ManyToOne
    private Client client;

    @ManyToOne
    @JoinColumn(name = "parkingId")
    private Parking parking;

    public Ticket(LocalDateTime enteredParkingAt, boolean isClientAtParking, LocalDateTime leftParkingAt,
                  Client client, Parking parking){
        this.enteredParkingAt = enteredParkingAt;
        this.isClientAtParking = isClientAtParking;
        this.leftParkingAt = leftParkingAt;
        this.client = client;
        this.parking = parking;
    }


}
