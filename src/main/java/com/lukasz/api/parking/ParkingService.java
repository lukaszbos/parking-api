package com.lukasz.api.parking;

import com.lukasz.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
class ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingMapper parkingMapper;

    @Autowired
    public ParkingService(ParkingRepository parkingRepository, ParkingMapper parkingMapper){
        this.parkingRepository = parkingRepository;
        this.parkingMapper = parkingMapper;
    }

    List<ParkingDto> getParkings(){
        List<Parking> parkings = new ArrayList<>();
        parkingRepository.findAll()
                .forEach(parkings::add);
        return parkings
                .stream()
                .map(parking -> parkingMapper.toDTO(parking))
                .collect(Collectors.toList());
    }

    ParkingDto getParking(Long parkingId){
        Parking parking = parkingRepository.findById(parkingId).orElseThrow(() -> new NotFoundException("Parking not Found :D :D :D"));
        return parkingMapper.toDTO(parking);
    }

    ParkingDto addParking(ParkingDto parkingDTO){
        Parking parking = parkingMapper.toModel(parkingDTO);
        Parking addedParking = parkingRepository.save(parking);
        return parkingMapper.toDTO(addedParking);
    }

    ParkingDto updateParking(ParkingDto parkingDTO, Long parkingId) {
        Parking parking = parkingMapper.toModel(parkingDTO);
        parking.setParkingId(parkingId);
        Parking addedParking = parkingRepository.save(parking);
        return parkingMapper.toDTO(addedParking);
    }

     ParkingDto deleteParking(Long parkingId) {
        Parking parking = parkingRepository.findById(parkingId).orElseThrow(() -> new NotFoundException (" Couldn't delete - parking not found  "));
        parkingRepository.deleteById(parkingId);
        return parkingMapper.toDTO(parking);
    }
}