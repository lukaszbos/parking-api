package com.lukasz.parking;

import com.lukasz.exception.NotFoundException;
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

    List<ParkingDTO> getParkings(){
        List<Parking> parkings = new ArrayList<>();
        parkingRepository.findAll()
                .forEach(parkings::add);
        return parkings
                .stream()
                .map(parking -> parkingMapper.toDTO(parking))
                .collect(Collectors.toList());
    }

    ParkingDTO getParking(Long parkingId){
        Parking parking = parkingRepository.findById(parkingId).orElseThrow(() -> new NotFoundException("Parking not Found :D :D :D"));
        return parkingMapper.toDTO(parking);
    }

    ParkingDTO addParking(ParkingDTO parkingDTO){
        Parking parking = parkingMapper.toModel(parkingDTO);
        Parking addedParking = parkingRepository.save(parking);
        return parkingMapper.toDTO(addedParking);
    }

    ParkingDTO  updateParking(ParkingDTO parkingDTO, Long parkingId) {
        Parking parking = parkingMapper.toModel(parkingDTO);
        parking.setParkingId(parkingId);
        Parking addedParking = parkingRepository.save(parking);
        return parkingMapper.toDTO(addedParking);
    }

     ParkingDTO deleteParking(Long parkingId) {
        Parking parking = parkingRepository.findById(parkingId).orElseThrow(() -> new NotFoundException (" Couldn't delete - parking not found  "));
        parkingRepository.deleteById(parkingId);
        return parkingMapper.toDTO(parking);
    }
}