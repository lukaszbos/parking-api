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
import java.util.stream.Collectors;

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

    List<BillDTO> getBills(Long parkingId, UUID clientId)  {
        List<Bill> bills = new ArrayList<>();
        if (!isNull(parkingId) && !isNull(clientId))
            billRepository.findByParking_ParkingIdAndClient_ClientId(parkingId, clientId).forEach(bills::add);
        if (!isNull(parkingId) && isNull(clientId))
            billRepository.findByParking_ParkingId(parkingId).forEach(bills::add);
        if (isNull(parkingId) && !isNull(clientId))
            billRepository.findByClient_ClientId(clientId).forEach(bills::add);
        else
            getAllBills();
        return bills
                .stream()
                .map(bill -> billMapper.toDTO(bill))
                .collect(Collectors.toList());
    }

    private List<BillDTO> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        billRepository.findAll().forEach(bills::add);
        return bills
                .stream()
                .map(bill -> billMapper.toDTO(bill))
                .collect(Collectors.toList());
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

    private Client getClientById(UUID clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Client not found - in bill"));

    }

    BillDTO updateBill(BillDTO billDTO, Long billId) {
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