package com.lukasz.parking;

import org.springframework.data.repository.CrudRepository;

public interface ParkingRepository extends CrudRepository<Parking, Long> {
}
