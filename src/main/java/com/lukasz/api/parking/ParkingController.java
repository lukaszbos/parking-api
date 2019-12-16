package com.lukasz.api.parking;

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
@RequestMapping("/parkings")
public class ParkingController {

    private ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping()
    public ResponseEntity<List<ParkingDto>> getAllParkings() {
        List<ParkingDto> parkingDtos = parkingService.getParkings();
        return new ResponseEntity<>(parkingDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{parkingId}")
    public ResponseEntity<ParkingDto> getParking(@PathVariable Long parkingId) {
        ParkingDto parkingDto = parkingService.getParking(parkingId);
        return new ResponseEntity<>(parkingDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ParkingDto> addParking(@RequestBody ParkingDto parkingDto) {
        ParkingDto responseDto = parkingService.addParking(parkingDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{parkingId}")
    public ResponseEntity<ParkingDto> updateParking(@RequestBody ParkingDto parkingDto, @PathVariable Long parkingId) {
        ParkingDto updatedParkingDto = parkingService.updateParking(parkingDto, parkingId);
        return new ResponseEntity<>(updatedParkingDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{parkingId}")
    public ResponseEntity<ParkingDto> deleteParking(@PathVariable Long parkingId) {
        parkingService.deleteParking(parkingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}