package com.lukasz.api.bill;

import com.lukasz.api.carpark.CarPark;
import com.lukasz.api.client.Client;
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
    private CarPark carPark;
}
