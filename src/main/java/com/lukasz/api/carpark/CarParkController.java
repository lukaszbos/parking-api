package com.lukasz.api.carpark;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/car-parks")
public class CarParkController {

    private CarParkService carParkService;

    @Autowired
    public CarParkController(CarParkService carParkService) {
        this.carParkService = carParkService;
    }

    @GetMapping()
    public ResponseEntity<List<CarParkDto>> getAllCarParks() {
        List<CarParkDto> responseDto = carParkService.getParks();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{carParkId}")
    public ResponseEntity<CarParkDto> getCarPark(@PathVariable Long carParkId) {
        CarParkDto carParkDto = carParkService.getCarPark(carParkId);
        return new ResponseEntity<>(carParkDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CarParkDto> addCarPark(@RequestBody CarParkDto carParkDto) {
        CarParkDto responseDto = carParkService.addCarPark(carParkDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{carParkId}")
    public ResponseEntity<CarParkDto> updateParking(@RequestBody CarParkDto carParkDto, @PathVariable Long carParkId) {
        CarParkDto updatedCarParkDto = carParkService.updateCarPark(carParkDto, carParkId);
        return new ResponseEntity<>(updatedCarParkDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carParkId}")
    public ResponseEntity<CarParkDto> deleteCarPark(@PathVariable Long carParkId) {
        carParkService.deleteCarPark(carParkId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}