package com.lukasz.ticket;

public class VirtualTicketMapper {

    VirtualTicket toModel(VirtualTicketDto virtualTicketDto){
        return new VirtualTicket(virtualTicketDto.getEnteredParkingAt(), virtualTicketDto.isClientAtParking(),
                virtualTicketDto.getLeftParkingAt(), virtualTicketDto.getClient());
    }


    VirtualTicketDto toDto(VirtualTicket virtualTicket){
        return new VirtualTicketDto(virtualTicket.getVirtualTicketId(), virtualTicket.getEnteredParkingAt(),
                virtualTicket.isClientAtParking(), virtualTicket.getLeftParkingAt(), virtualTicket.getClient());
    }
}
