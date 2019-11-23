package com.lukasz.bill;

import com.lukasz.client.Client;
import com.lukasz.parking.Parking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Long billId;
    private Integer numberOfHours;
    private BigDecimal cost;
    private Parking parking;
    private Client client;
}
