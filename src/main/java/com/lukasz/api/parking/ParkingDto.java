package com.lukasz.api.parking;

import com.lukasz.api.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDto {
    private Long parkingId;
    private String name;
    private Address address;
}