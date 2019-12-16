package com.lukasz.api.management;

import com.lukasz.api.ticket.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/management")
public class ManagementController {

    private final ManagementService managementService;

    @Autowired
    ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping
    public ResponseEntity<TicketDto> addTicketAndBill(@RequestBody ManagementDto managementDto) {
        TicketDto responseDto = managementService.addTicket(managementDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //TODO ZROBIC ZEBY TUTAJ MGMT DTO BYLO ZWRACANE
    @PatchMapping(value = "/{clientId}")
    public ResponseEntity<TicketDto> updateTicketAndBill(@RequestBody ManagementDto managementDto, @PathVariable UUID clientId) {
        TicketDto responseDto = managementService.updateTicketAndAddBill(managementDto, clientId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

}
