package com.lukasz.api.address;

import com.lukasz.api.carpark.CarParkDto;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toModel(CarParkDto carParkDto){
        return new Address(carParkDto.getAddress().getCity(), carParkDto.getAddress().getStreet(),
                carParkDto.getAddress().getHouseNumber(),
                carParkDto.getAddress().getLatitude(), carParkDto.getAddress().getLongitude());
    }

}
