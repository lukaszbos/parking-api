package com.lukasz.ticket;

import com.lukasz.client.Client;
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
public class VirtualTicket {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long virtualTicketId;
    private LocalDateTime enteredParkingAt;
    private boolean isClientAtParking;
    private LocalDateTime leftParkingAt;

    @JoinColumn(name = "clientId")
    @ManyToOne
    private Client client;

    public VirtualTicket(LocalDateTime enteredParkingAt, boolean isClientAtParking, LocalDateTime leftParkingAt, Client client){
        this.enteredParkingAt = enteredParkingAt;
        this.isClientAtParking = isClientAtParking;
        this.leftParkingAt = leftParkingAt;
        this.client = client;
    }

}
