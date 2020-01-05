package com.lukasz.api.parking;

import com.lukasz.api.address.Address;
import com.lukasz.api.address.AddressMapper;
import com.lukasz.api.address.AddressRepository;
import com.lukasz.api.exception.NotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
class ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingMapper parkingMapper;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    public ParkingService(ParkingRepository parkingRepository, ParkingMapper parkingMapper,
                          AddressRepository addressRepository, AddressMapper addressMapper) {
        this.parkingRepository = parkingRepository;
        this.parkingMapper = parkingMapper;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    List<ParkingDto> getParkings() {
        List<Parking> parkings = new ArrayList<>();
        parkingRepository.findAll()
                .forEach(parkings::add);
        return parkings
                .stream()
                .map(parking -> parkingMapper.toDTO(parking))
                .collect(Collectors.toList());
    }

    ParkingDto getParking(Long parkingId) {
        Parking parking = parkingRepository.findById(parkingId).orElseThrow(() -> new NotFoundException("Parking not Found :D :D :D"));
        return parkingMapper.toDTO(parking);
    }

    ParkingDto addParking(ParkingDto parkingDTO) {
        Parking parking = parkingMapper.toModel(parkingDTO);
        Address address = addressMapper.toModel(parkingDTO);
        Address addedAddress = addressRepository.save(address);
        parking.setAddress(addedAddress);

        Parking addedParking = parkingRepository.save(parking);
        return parkingMapper.toDTO(addedParking);
    }



    ParkingDto updateParking(ParkingDto parkingDTO, Long parkingId) {
        Parking parking = parkingMapper.toModel(parkingDTO);
        parking.setParkingId(parkingId);

        Address address = addressMapper.toModel(parkingDTO);
        logger.info(parkingDTO.getAddress().getAddressId());
        getAddressById(parkingDTO.getAddress().getAddressId()); //check if addressId was passed
        address.setAddressId(parkingDTO.getAddress().getAddressId());

        Address addedAddress = addressRepository.save(address);
        parking.setAddress(addedAddress);

        Parking addedParking = parkingRepository.save(parking);
        return parkingMapper.toDTO(addedParking);
    }

    private Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow(() -> new NotFoundException("Address not found - in parking"));
    }

    ParkingDto deleteParking(Long parkingId) {
        Parking parking = parkingRepository.findById(parkingId).orElseThrow(() -> new NotFoundException(" Couldn't delete - parking not found  "));
        parkingRepository.deleteById(parkingId);
        return parkingMapper.toDTO(parking);
    }
}