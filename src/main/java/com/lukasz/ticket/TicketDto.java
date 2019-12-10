package com.lukasz.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lukasz.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long ticketId;
    private LocalDateTime enteredParkingAt;
    private boolean isClientAtParking;
    private LocalDateTime leftParkingAt;
    private Client client;

    public TicketDto(LocalDateTime enteredParkingAt, boolean isClientAtParking, Client client) {
        this.enteredParkingAt = enteredParkingAt;
        this.isClientAtParking = isClientAtParking;
        this.client = client;
    }
}

