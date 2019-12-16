package com.lukasz.api.ticket;

import com.lukasz.api.client.Client;
import com.lukasz.api.parking.Parking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long ticketId;
    private LocalDateTime enteredParkingAt;
    private boolean isClientAtParking;
    private LocalDateTime leftParkingAt;
    private Client client;
    private Parking parking;

    public TicketDto(LocalDateTime enteredParkingAt, boolean isClientAtParking, Client client, Parking parking) {
        this.enteredParkingAt = enteredParkingAt;
        this.isClientAtParking = isClientAtParking;
        this.client = client;
        this.parking = parking;
    }
}

