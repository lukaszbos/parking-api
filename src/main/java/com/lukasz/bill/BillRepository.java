package com.lukasz.bill;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Integer> {
    List<Bill> findByParking_ParkingId(Integer parkingId);
}