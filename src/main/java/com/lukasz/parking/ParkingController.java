package com.lukasz.parking;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Parking> getAllParkings() {
        return parkingService.getParkings();
    }

    @GetMapping(value = "/{parkingId}")
    public Parking getParking(@PathVariable Integer parkingId) {
        return parkingService.getParking(parkingId);
    }

    @PostMapping()
    public void addParking(@RequestBody Parking parking) {
        parkingService.addParking(parking);
    }

    @PutMapping(value = "/{parkingId}")
    public void updateParking(@RequestBody Parking parking, @PathVariable Integer parkingId) {
        parkingService.updateParking(parking, parkingId);
    }

    @DeleteMapping(value = "/{parkingId}")
    public void deleteParking(@PathVariable Integer parkingId) {
        parkingService.deleteParking(parkingId);
    }
}