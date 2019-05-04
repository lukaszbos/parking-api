package com.lukasz.parking;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
class ParkingService {
    @Autowired
    private ParkingRepository parkingRepository;

    List<Parking> getParkings(){
        List<Parking> parkings = new ArrayList<>();
        parkingRepository.findAll()
                .forEach(parkings::add);
        return parkings;
    }

    Parking getParking(Integer parkingId){
        return parkingRepository.findById(parkingId).orElse(null);
    }

    void addParking(Parking parking){
        parkingRepository.save(parking);
    }

    void updateParking(Parking parking, Integer parkingId) {
        parkingRepository.save(parking);
    }

    void deleteParking(Integer parkingId) {
        parkingRepository.deleteById(parkingId);
    }
}