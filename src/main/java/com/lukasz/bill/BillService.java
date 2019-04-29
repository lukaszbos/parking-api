package com.lukasz.bill;

import com.lukasz.parking.Parking;
import com.lukasz.parking.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    private ParkingRepository parkingRepository;
    private BillRepository billRepository;

    @Autowired
    public BillService(ParkingRepository parkingRepository, BillRepository billRepository) {
        this.parkingRepository = parkingRepository;
        this.billRepository = billRepository;
    }

    List<Bill> getBills(Integer parkingId) {
        if (isParkingIdSent(parkingId)) {
            return getBillsByParkingId(parkingId);
        } else
            return getAllBills();
    }

    private boolean isParkingIdSent(Integer parkingId) {
        return parkingId != null;
    }

    private List<Bill> getBillsByParkingId(Integer parkingId) {
        List<Bill> bills = new ArrayList<>();
        billRepository.findByParking_ParkingId(parkingId).forEach(bills::add);
        return bills;
    }

    private List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        billRepository.findAll().forEach(bills::add);
        return bills;
    }

    void addBill(Bill bill, Integer parkingId) {
        Parking parkingOnWithBillIs = getAccurateParking(parkingId);
        bill.setParking(parkingOnWithBillIs);
        billRepository.save(bill);
    }

    private Parking getAccurateParking(Integer parkingId) {
        return parkingRepository.findById(parkingId).get();
    }

    Bill getBillById(Integer billId) {
        return billRepository.findById(billId).get();
    }

    void updateBill(Bill bill, Integer parkingId) {
        Parking parkingOnWithBillIs = getAccurateParking(parkingId);
        bill.setParking(parkingOnWithBillIs);
        billRepository.save(bill);
    }

    void deleteBill(Integer billId) {
        billRepository.deleteById(billId);
    }
}