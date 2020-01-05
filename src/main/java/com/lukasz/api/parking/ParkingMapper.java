package com.lukasz.api.parking;

import org.springframework.stereotype.Component;

@Component
public class ParkingMapper {

    Parking toModel(ParkingDto parkingDTO) {
        return new Parking(parkingDTO.getName(), parkingDTO.getAddress());
    }

    ParkingDto toDTO(Parking parking) {
        return new ParkingDto(parking.getParkingId(), parking.getName(), parking.getAddress());
    }
}
