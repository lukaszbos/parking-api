package com.lukasz.api.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(value = "/{ticketId}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long ticketId) {
        TicketDto ticketDto = ticketService.getTicketById(ticketId);
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }
/*
    @GetMapping
    public ResponseEntity<List<TicketDto>> getTicketByClientId(@RequestParam(name = "clientId") UUID clientId) {
        List<TicketDto> ticketDtos = ticketService.getTicketsOfClient(clientId);
        return new ResponseEntity<>(ticketDtos, HttpStatus.OK);
    }
*/
    @GetMapping
    ResponseEntity<TicketDto> getLastBillOfClient(@RequestParam(name = "clientId") UUID clientId) {
        TicketDto billDto = ticketService.getLastTicketOfClient(clientId);
        return new ResponseEntity<>(billDto, HttpStatus.OK);
    }

    @PostMapping
    //2019-12-08T14:18:43.064+0000
    public ResponseEntity<TicketDto> addTicket(@RequestBody TicketDto ticketDto) {
        TicketDto responseDto = ticketService.addTicket(ticketDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{ticketId}")
    public ResponseEntity<TicketDto> updatedTicket(@RequestBody TicketDto ticketDto, @PathVariable Long ticketId) {
        TicketDto responseDto = ticketService.updateTicket(ticketDto, ticketId);
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}