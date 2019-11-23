package com.lukasz.parking;

import org.springframework.stereotype.Component;

@Component
public class ParkingMapper {
    public Parking toModel(ParkingDTO parkingDTO){
        return new Parking(parkingDTO.getName());
    }

    public ParkingDTO toDTO(Parking parking){
        return new ParkingDTO(parking.getParkingId(), parking.getName());
    }
}
