package com.lukasz.ticket;

import com.lukasz.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VirtualTicketDto {
    private Long virtualTicketId;
    private LocalDateTime enteredParkingAt;
    private boolean isClientAtParking;
    private LocalDateTime leftParkingAt;
    private Client client;
}

