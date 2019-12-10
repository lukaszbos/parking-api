package com.lukasz.bill;

import com.lukasz.client.Client;
import com.lukasz.parking.Parking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDtoPost {
    private Long billId;
    private Parking parking;
    private Client client;
    private LocalDateTime enteredParkingAt;
}
