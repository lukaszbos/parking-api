package com.lukasz.api.carpark;

import org.springframework.stereotype.Component;

@Component
public class CarParkMapper {

    CarPark toModel(CarParkDto carParkDTO) {
        return new CarPark(carParkDTO.getName(), carParkDTO.getAddress());
    }

    CarParkDto toDTO(CarPark carPark) {
        return new CarParkDto(carPark.getCarParkId(), carPark.getName(), carPark.getAddress());
    }
}
