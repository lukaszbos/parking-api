package com.lukasz.bill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByParking_ParkingId(Long parkingId);
    List<Bill> findByClient_ClientId(UUID clientId);
    List<Bill> findByParking_ParkingIdAndClient_ClientId(Long parkingId, UUID clientId);
}