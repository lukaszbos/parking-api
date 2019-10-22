package com.lukasz.bill;

import com.lukasz.client.Client;
import com.lukasz.client.ClientRepository;
import com.lukasz.parking.Parking;
import com.lukasz.parking.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class BillService {
    private ParkingRepository parkingRepository;
    private BillRepository billRepository;
    private ClientRepository clientRepository;

    @Autowired
    public BillService(ParkingRepository parkingRepository, BillRepository billRepository, ClientRepository clientRepository) {
        this.parkingRepository = parkingRepository;
        this.billRepository = billRepository;
        this.clientRepository = clientRepository;
    }

    List<Bill> getBills(Integer parkingId, Integer clientId) {
        return getBillsById(parkingId, clientId);
    }


    private List<Bill> getBillsById(Integer parkingId, Integer clientId) {
        List<Bill> bills = new ArrayList<>();
        if (!isNull(parkingId) && !isNull(clientId))
            billRepository.findByParking_ParkingIdAndClient_ClientId(parkingId, clientId).forEach(bills::add);
        if (!isNull(parkingId) && isNull(clientId))
            billRepository.findByParking_ParkingId(parkingId).forEach(bills::add);
        if (isNull(parkingId) && !isNull(clientId))
            billRepository.findByClient_ClientId(clientId).forEach(bills::add);
        else
            getAllBills();
        return bills;
    }

    private List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        billRepository.findAll().forEach(bills::add);
        return bills;
    }

    void addBill(Bill bill) {
        assembleTheBill(bill);
        billRepository.save(bill);
    }

    private void assembleTheBill(Bill bill) {
        Parking parkingOnBill = assembleParking(bill);
        bill.setParking(parkingOnBill);

        Client clientOnBill = assembleClient(bill);
        bill.setClient(clientOnBill);
    }

    private Parking assembleParking(Bill bill) {
        Integer parkingIdFromBill = bill.getParking().getParkingId();
        String parkingNameFromBill = bill.getParking().getName();

        if (isThisParkingInMyRepo(parkingIdFromBill))
            return getParkingById(parkingIdFromBill);
        else
            return new Parking(parkingIdFromBill, parkingNameFromBill);
    }

    private boolean isThisParkingInMyRepo(Integer parkingIdFromBill) {
        return parkingRepository.existsById(parkingIdFromBill);
    }

    private Parking getParkingById(Integer parkingId) {
        return parkingRepository.findById(parkingId).get();
    }

    private Client assembleClient(Bill bill) {
        Integer clientIdFromBill = bill.getClient().getClientId();
        String clientNameFromBill = bill.getClient().getName();
        String clientSurnameFromBill = bill.getClient().getSurname();

        if (isThisClientInMyRepo(clientIdFromBill))
            return getClientById(clientIdFromBill);
        else
            return new Client(clientIdFromBill, clientNameFromBill, clientSurnameFromBill);
    }

    private boolean isThisClientInMyRepo(Integer clientIdFromBill) {
        return clientRepository.existsById(clientIdFromBill);
    }

    private Client getClientById(Integer clientId) {
        return clientRepository.findById(clientId).get();
    }

    Bill getBillById(Integer billId) {
        return billRepository.findById(billId).get();
    }

    void updateBill(Bill bill) {
        assembleTheBill(bill);
        billRepository.save(bill);
    }

    void deleteBill(Integer billId) {
        billRepository.deleteById(billId);
    }
}