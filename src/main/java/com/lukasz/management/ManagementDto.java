package com.lukasz.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lukasz.client.Client;
import com.lukasz.parking.Parking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagementDto {
    private Long billId;
    private Parking parking;
    private Client client;
    private LocalDateTime enteredParkingAt;
    private LocalDateTime leftParkingAt;

    private Long ticketId;
    @JsonProperty
    private boolean isClientAtParking;

}
