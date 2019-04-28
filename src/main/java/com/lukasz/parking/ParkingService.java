package com.lukasz.parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingService {
    @Autowired
    private ParkingRepository parkingRepository;

    public List<Parking> getParkings(){
        List<Parking> parkings = new ArrayList<>();
        parkingRepository.findAll()
                .forEach(parkings::add);
        return parkings;
    }

    public Parking getParking(Integer parkingId){
        return parkingRepository.findById(parkingId).orElse(null);
    }

    public void addParking(Parking parking){
        parkingRepository.save(parking);
    }

    public void updateParking(Parking parking, Integer parkingId) {
        parkingRepository.save(parking);
    }

    public void deleteParking( Integer parkingId) {
        parkingRepository.deleteById(parkingId);
    }
}
