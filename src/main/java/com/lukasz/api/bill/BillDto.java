package com.lukasz.api.bill;

import com.lukasz.api.client.Client;
import com.lukasz.api.parking.Parking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Long billId;
    private Integer numberOfHours;
    private BigDecimal cost;
    private Client client;
    private Parking parking;
}
