package com.lukasz.bill;

import com.lukasz.client.Client;
import com.lukasz.client.ClientRepository;
import com.lukasz.exception.NotFoundException;
import com.lukasz.parking.Parking;
import com.lukasz.parking.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class BillService {
    private final ParkingRepository parkingRepository;
    private final BillRepository billRepository;
    private final ClientRepository clientRepository;
    private final BillMapper billMapper;

    @Autowired
    public BillService(ParkingRepository parkingRepository, BillRepository billRepository, ClientRepository clientRepository, BillMapper billMapper) {
        this.parkingRepository = parkingRepository;
        this.billRepository = billRepository;
        this.clientRepository = clientRepository;
        this.billMapper = billMapper;
    }

    List<Bill> getBills(Long parkingId, UUID clientId) {
        return getBillsById(parkingId, clientId);
    }


    private List<Bill> getBillsById(Long parkingId, UUID clientId) {
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

    BillDTO getBillById(Long billId) {
        Bill bill = billRepository.findById(billId).orElseThrow(() -> new NotFoundException("Bill not Found :D :D"));
        return billMapper.toDTO(bill);
    }

    BillDTO addBill(BillDTO billDTO) {
        Bill bill = billMapper.toModel(billDTO);
        bill.setParking(getParkingById(billDTO.getParking().getParkingId()));
        bill.setClient(getClientById(billDTO.getClient().getClientId()));
        Bill addedBill = billRepository.save(bill);
        return billMapper.toDTO(addedBill);
    }

    private Parking getParkingById(Long parkingId) {
        return parkingRepository.findById(parkingId).orElseThrow(() -> new NotFoundException("Parking not Found :D :D :D"));
    }

    private Client getClientById(UUID clientId){
        return clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Client not found - in bill"));

    }



    /*
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
*/

    BillDTO updateBill(BillDTO billDTO, Long billId) {
        //assembleTheBill(bill);
        Bill bill = billMapper.toModel(billDTO);
        bill.setBillId(billId);
        bill.setParking(getParkingById(billDTO.getParking().getParkingId()));
        bill.setClient(getClientById(billDTO.getClient().getClientId()));
        Bill addedBill = billRepository.save(bill);
        return billMapper.toDTO(addedBill);
    }

    BillDTO deleteBill(Long billId) {
        Bill bill = billRepository.findById(billId).orElseThrow(() -> new NotFoundException("Bill not Found :D :D"));
        billRepository.deleteById(billId);
        return billMapper.toDTO(bill);
    }
}