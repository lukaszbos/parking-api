package com.lukasz.api.carpark;

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
class CarParkService {

    private final CarParkRepository carParkRepository;
    private final CarParkMapper carParkMapper;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    public CarParkService(CarParkRepository carParkRepository, CarParkMapper carParkMapper,
                          AddressRepository addressRepository, AddressMapper addressMapper) {
        this.carParkRepository = carParkRepository;
        this.carParkMapper = carParkMapper;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    List<CarParkDto> getParks() {
        List<CarPark> carParks = new ArrayList<>();
        carParkRepository.findAll()
                .forEach(carParks::add);
        return carParks
                .stream()
                .map(parking -> carParkMapper.toDTO(parking))
                .collect(Collectors.toList());
    }

    CarParkDto getCarPark(Long carParkId) {
        CarPark carPark = carParkRepository.findById(carParkId).orElseThrow(() -> new NotFoundException("Parking not Found"));
        return carParkMapper.toDTO(carPark);
    }

    CarParkDto addCarPark(CarParkDto carParkDTO) {
        CarPark carPark = carParkMapper.toModel(carParkDTO);
        Address address = addressMapper.toModel(carParkDTO);
        Address addedAddress = addressRepository.save(address);
        carPark.setAddress(addedAddress);

        CarPark addedCarPark = carParkRepository.save(carPark);
        return carParkMapper.toDTO(addedCarPark);
    }



    CarParkDto updateCarPark(CarParkDto carParkDTO, Long carParkId) {
        CarPark carPark = carParkMapper.toModel(carParkDTO);
        carPark.setCarParkId(carParkId);

        Address address = addressMapper.toModel(carParkDTO);
        logger.info(carParkDTO.getAddress().getAddressId());
        getAddressById(carParkDTO.getAddress().getAddressId()); //check if addressId was passed
        address.setAddressId(carParkDTO.getAddress().getAddressId());

        Address addedAddress = addressRepository.save(address);
        carPark.setAddress(addedAddress);

        CarPark addedCarPark = carParkRepository.save(carPark);
        return carParkMapper.toDTO(addedCarPark);
    }

    private Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow(() -> new NotFoundException("Address not found - in parking"));
    }

    CarParkDto deleteCarPark(Long carParkId) {
        CarPark carPark = carParkRepository.findById(carParkId).orElseThrow(() -> new NotFoundException(" Couldn't delete - parking not found  "));
        carParkRepository.deleteById(carParkId);
        return carParkMapper.toDTO(carPark);
    }
}