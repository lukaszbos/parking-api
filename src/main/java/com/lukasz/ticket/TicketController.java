package com.lukasz.ticket;

import com.lukasz.bill.Bill;
import com.lukasz.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NamedStoredProcedureQueries;
import java.time.LocalDateTime;
import java.util.List;
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
        //TODO tutaj powinno być wywołanie controllera BILL, żeby na podstawie gotowego ticketu zrobił billa
        /*
        Stowrzyć sub serwis który będzie zawierał serwis ticket oraz serwis bill. Bedzie on wywoływany w serwisie ticket na PUT i przyjmował na starcie wyjscie tego PUTa a potem tą z wyjścia obliczał cost i tą wartość przypisywał do nowego billa
        Tylko teraz pytanie skąd wziąć pozostałe wartości do Billa?

        Cały wielki request powinien iść do serwisu głównego i potem być rozdzielany
        na bill i ticket chyba??

         */

        //Bill newBill = new Bill();
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}