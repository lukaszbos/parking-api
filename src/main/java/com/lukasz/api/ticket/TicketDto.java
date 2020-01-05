package com.lukasz.api.ticket;

import com.lukasz.api.carpark.CarPark;
import com.lukasz.api.client.Client;
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
    private CarPark carPark;

    public TicketDto(LocalDateTime enteredParkingAt, boolean isClientAtParking, Client client, CarPark carPark) {
        this.enteredParkingAt = enteredParkingAt;
        this.isClientAtParking = isClientAtParking;
        this.client = client;
        this.carPark = carPark;
    }
}

