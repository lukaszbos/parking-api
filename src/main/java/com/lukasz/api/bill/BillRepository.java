package com.lukasz.api.bill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, Long> {
    //List<Bill> findByParking_ParkingId(Long parkingId);
    List<Bill> findByClient_ClientId(UUID clientId);
    //List<Bill> findByParking_ParkingIdAndClient_ClientId(Long parkingId, UUID clientId);
    //Bill findTop1ByClient_ClientIdOrderByBillIdDesc(UUID clientId);

    //@Query(value = "SELECT * from Bill b where b.client = (select * from Client c where c.clientId = :clientId) ORDER BY b.billId DESC LIMIT 1", nativeQuery = true)
    //Bill findLastBillOfClient(@Param("clientId") UUID clientId);
}