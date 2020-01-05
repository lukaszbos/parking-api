package com.lukasz.api.carpark;

import com.lukasz.api.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarParkDto {
    private Long carParkId;
    private String name;
    private Address address;
}