package com.lukasz.api.bill;

import com.lukasz.api.client.Client;
import com.lukasz.api.client.ClientRepository;
import com.lukasz.api.exception.NotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BillService {
    //private final ParkingRepository parkingRepository;
    private final BillRepository billRepository;
    private final ClientRepository clientRepository;
    private final BillMapper billMapper;

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    public BillService(BillRepository billRepository, ClientRepository clientRepository,
                       BillMapper billMapper) {
        this.billRepository = billRepository;
        this.clientRepository = clientRepository;
        this.billMapper = billMapper;
    }

    /*
        List<BillDto> getBills(Long parkingId, UUID clientId) {
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
                    .map(bill -> billMapper.toDto(bill))
                    .collect(Collectors.toList());
        }
    */
    BillDto getLastBillOfClient(UUID clientId) {
        BillDto billDto;
        //Bill bill = billRepository.findTop1ByClient_ClientIdOrderByBillIdDesc(clientId);
        //logger.info("Ostatni paragon klienta : " + bill);
        //return billMapper.toDto(bill);
        return null;
    }

    private List<BillDto> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        billRepository.findAll().forEach(bills::add);
        return bills
                .stream()
                .map(bill -> billMapper.toDto(bill))
                .collect(Collectors.toList());
    }

    BillDto getBillById(Long billId) {
        Bill bill = billRepository.findById(billId).orElseThrow(() -> new NotFoundException("Bill not Found :D :D"));
        return billMapper.toDto(bill);
    }

    public BillDto addBill(BillDto billDto) {
        Bill bill = billMapper.toModel(billDto);
        //bill.setParking(getParkingById(billDto.getParking().getParkingId()));
        bill.setClient(getClientById(billDto.getClient().getClientId()));
        Bill addedBill = billRepository.save(bill);
        return billMapper.toDto(addedBill);
    }

    /*
        private Parking getParkingById(Long parkingId) {
            return parkingRepository.findById(parkingId).orElseThrow(() -> new NotFoundException("Parking not Found :D :D :D"));
        }
    */
    private Client getClientById(UUID clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Client not found - in bill"));

    }


    BillDto updateBill(BillDto billDTO, Long billId) {
        Bill bill = billMapper.toModel(billDTO);
        bill.setBillId(billId);
        //bill.setParking(getParkingById(billDTO.getParking().getParkingId()));
        bill.setClient(getClientById(billDTO.getClient().getClientId()));
        Bill addedBill = billRepository.save(bill);
        return billMapper.toDto(addedBill);
    }

/*
    BillDto updateBill(BillDtoPut billDtoPut, Long billId) {
        //BillDto billDTO = new BillDto();
        BillDto billDto = getBillById(billId);
    //    billDto.setLeftParkingAt(billDtoPut.getLeftParkingAt());

        Bill bill = billMapper.toModel(billDto);
        bill.setBillId(billId);
        bill.setParking(getParkingById(billDto.getParking().getParkingId()));
        bill.setClient(getClientById(billDto.getClient().getClientId()));
        Bill addedBill = billRepository.save(bill);
        return billMapper.toDto(addedBill);
    }
*/

    BillDto deleteBill(Long billId) {
        Bill bill = billRepository.findById(billId).orElseThrow(() -> new NotFoundException("Bill not Found :D :D"));
        billRepository.deleteById(billId);
        return billMapper.toDto(bill);
    }
}