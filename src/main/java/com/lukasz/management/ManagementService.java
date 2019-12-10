package com.lukasz.management;

import com.lukasz.bill.Bill;
import com.lukasz.bill.BillController;
import com.lukasz.bill.BillDto;
import com.lukasz.bill.BillDtoPost;
import com.lukasz.bill.BillMapper;
import com.lukasz.bill.BillService;
import com.lukasz.ticket.TicketDto;
import com.lukasz.ticket.TicketMapper;
import com.lukasz.ticket.TicketService;
import com.lukasz.utils.ChargeCalculator;
import com.lukasz.utils.Tariff;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static com.lukasz.utils.ChargeCalculator.calculateCharge;
import static java.util.logging.Level.INFO;

@Component
public class ManagementService {

    private final BillService billService;
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final BillMapper billMapper;
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    ManagementService(BillService billService, TicketService ticketService, TicketMapper ticketMapper, BillMapper billMapper) {
        this.billService = billService;
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
        this.billMapper = billMapper;
    }

    public TicketDto addTicket(ManagementDto managementDto) {
//        BillDto billDto = new BillDto(managementDto.getBillId(), managementDto.getParking(), managementDto.getClient());
//        billController.addBill(billDto);
//        ResponseEntity responseEntityBill = billController.addBill(billDto);
//        logger.info("Zwrotka z kontrolera bill: " + responseEntityBill);

        TicketDto ticketDto = new TicketDto(managementDto.getTicketId(), managementDto.getEnteredParkingAt(), managementDto.isClientAtParking(), managementDto.getLeftParkingAt(), managementDto.getClient());
        TicketDto responseDto = ticketService.addTicket(ticketDto);
        logger.info("Zwrotka z kontrolera ticket: " + responseDto);


        List<ResponseEntity> zwrotki = new ArrayList<>();

        return responseDto;
    }

    //TODO to wszystko powinno byc robione w mgmt maperze
    public BillDto updateTicketAndAddBill(ManagementDto managementDto, UUID clientId) {
        TicketDto ticketDto = ticketService.getLastTicketOfClient(clientId);
        logger.info("Taki ticket otrzymalem z mgmt " + ticketDto);

        ticketDto.setLeftParkingAt(managementDto.getLeftParkingAt());
        ticketDto.setClientAtParking(managementDto.isClientAtParking());
        ticketService.updateTicket(ticketDto, ticketDto.getTicketId());

        if (clientLeftParking(ticketDto)) {
            LocalDateTime parkingDate = ticketDto.getEnteredParkingAt();
            LocalDateTime leftParkingDate = ticketDto.getLeftParkingAt();
            Tariff tariff = new Tariff(BigDecimal.valueOf(10.29), BigDecimal.valueOf(8.00));
            BigDecimal cost = calculateCharge(parkingDate, leftParkingDate, tariff);
            //TODO WYJEBAC NUMBER OF HOURS? albo dorobić metode
            Bill bill = new Bill(0, cost, managementDto.getParking(), managementDto.getClient());
            BillDto billDto = billMapper.toDto(bill);
            BillDto addedBillDto = billService.addBill(billDto);
            return addedBillDto;
        }

        return null;
    }

    private boolean clientLeftParking(TicketDto requestDto) {
        return !requestDto.isClientAtParking();
    }

    /*
    Mogę putnąć ten ticket a potem go zgetować
    i wtedy wpisać do put billa
     */


}
