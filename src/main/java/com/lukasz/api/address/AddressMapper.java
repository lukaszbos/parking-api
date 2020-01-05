package com.lukasz.api.address;

import com.lukasz.api.parking.ParkingDto;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toModel(ParkingDto parkingDto){
        return new Address(parkingDto.getAddress().getCity(), parkingDto.getAddress().getStreet(),
                parkingDto.getAddress().getHouseNumber(),
                parkingDto.getAddress().getLatitude(), parkingDto.getAddress().getLongitude());
    }

}
