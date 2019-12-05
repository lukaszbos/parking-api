package com.lukasz.parking;

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
    public ResponseEntity<List<ParkingDTO>> getAllParkings() {
        List<ParkingDTO> parkingDTOS = parkingService.getParkings();
        return new ResponseEntity<>(parkingDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{parkingId}")
    public ResponseEntity<ParkingDTO> getParking(@PathVariable Long parkingId) {
        ParkingDTO parkingDTO = parkingService.getParking(parkingId);
        return new ResponseEntity<>(parkingDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ParkingDTO> addParking(@RequestBody ParkingDTO parkingDTO) {
        ParkingDTO addedParkingDTO = parkingService.addParking(parkingDTO);
        return new ResponseEntity<>(addedParkingDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{parkingId}")
    public ResponseEntity<ParkingDTO> updateParking(@RequestBody ParkingDTO parkingDTO, @PathVariable Long parkingId) {
        ParkingDTO updatedParkingDTO = parkingService.updateParking(parkingDTO, parkingId);
        return new ResponseEntity<>(updatedParkingDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{parkingId}")
    public ResponseEntity<ParkingDTO> deleteParking(@PathVariable Long parkingId) {
        parkingService.deleteParking(parkingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}