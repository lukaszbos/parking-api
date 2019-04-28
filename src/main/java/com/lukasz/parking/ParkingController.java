package com.lukasz.parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @RequestMapping("/parkings")
    public List<Parking> getAllParkings() {
        return parkingService.getParkings();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/parkings/{parkingId}")
    public Parking getParking(@PathVariable Integer parkingId) {
        return parkingService.getParking(parkingId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/parkings")
    public void addParking(@RequestBody Parking parking) {
        Parking parking1 = new Parking(parking);
        parkingService.addParking(parking1);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/parkings/{parkingId}")
    public void updateParking(@RequestBody Parking parking, @PathVariable Integer parkingId) {
        parkingService.updateParking(parking, parkingId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/parkings/{parkingId}")
    public void deleteParking(@PathVariable Integer parkingId) {
        parkingService.deleteParking(parkingId);
    }
}
