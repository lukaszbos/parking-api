package com.lukasz.bill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByParking_ParkingId(Integer parkingId);
    List<Bill> findByClient_ClientId(Integer clientId);
    List<Bill> findByParking_ParkingIdAndClient_ClientId(Integer parkingId, Integer clientId);
}