package com.lukasz.ticket;

import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public Ticket toModel(TicketDto ticketDto){
        return new Ticket(ticketDto.getEnteredParkingAt(), ticketDto.isClientAtParking(),
                ticketDto.getLeftParkingAt(), ticketDto.getClient());
    }


    public TicketDto toDto(Ticket ticket){
        return new TicketDto(ticket.getTicketId(), ticket.getEnteredParkingAt(),
                ticket.isClientAtParking(), ticket.getLeftParkingAt(), ticket.getClient());
    }
}
