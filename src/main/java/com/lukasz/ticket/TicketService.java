package com.lukasz.ticket;

import com.lukasz.client.Client;
import com.lukasz.client.ClientRepository;
import com.lukasz.exception.NotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lukasz.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final ClientRepository clientRepository;

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper, ClientRepository clientRepository){
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.clientRepository = clientRepository;
    }

    public TicketDto getTicketById(Long TicketId){
        Ticket ticket = ticketRepository.findById(TicketId).orElseThrow(() -> new NotFoundException("Ticket not found "));
        return ticketMapper.toDto(ticket);
    }

    List<TicketDto> getTicketsOfClient(UUID clientId){
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findByClient_ClientId(clientId).forEach(ticket -> tickets.add(ticket));

        return tickets
                .stream()
                .map(ticket -> ticketMapper.toDto(ticket))
                .collect(Collectors.toList());
    }

    public TicketDto addTicket(TicketDto ticketDto){
        Ticket ticket = ticketMapper.toModel(ticketDto);
        ticket.setClient(getClientById(ticketDto.getClient().getClientId()));
        Ticket addedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDto(addedTicket);
    }

    public Client getClientById(UUID clientId){
        return clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Client not Found - ticket"));
    }


    public TicketDto updateTicket(TicketDto ticketDto, Long ticketId){
        Ticket ticket = ticketMapper.toModel(ticketDto);
        ticket.setTicketId(ticketId);
        ticket.setClient(getClientById(ticketDto.getClient().getClientId()));
        ticket.setEnteredParkingAt(getTicketById(ticketId).getEnteredParkingAt());
        //ticket.setClientAtParking(false);
        Ticket updatedTicket = ticketRepository.save(ticket);

        TicketDto returnDto = ticketMapper.toDto(updatedTicket);

        return returnDto;
    }

    public TicketDto getLastTicketOfClient(UUID clientId) {
        Ticket ticket = ticketRepository.findTop1ByClient_ClientIdOrderByTicketIdDesc(clientId).orElseThrow(() -> new NotFoundException("This client has no tickets "));
        logger.info("Ostatni ticket klienta : " + ticket);
        return ticketMapper.toDto(ticket);

    }
}
