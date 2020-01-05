package com.lukasz.api.ticket;

import com.lukasz.api.carpark.CarPark;
import com.lukasz.api.client.Client;
import com.lukasz.api.client.ClientRepository;
import com.lukasz.api.exception.ConflictException;
import com.lukasz.api.exception.NotFoundException;
import com.lukasz.api.carpark.CarParkRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final ClientRepository clientRepository;
    private final CarParkRepository carParkRepository;

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper, ClientRepository clientRepository, CarParkRepository carParkRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.clientRepository = clientRepository;
        this.carParkRepository = carParkRepository;
    }

    /*
        List<TicketDto> getTickets(Long parkingId, UUID clientId) {
            List<Ticket> tickets = new ArrayList<>();
            if (!isNull(parkingId) && !isNull(clientId))
                ticketRepository.findByParking_ParkingIdAndClient_ClientId(parkingId, clientId).forEach(tickets::add);
            if (!isNull(parkingId) && isNull(clientId))
                ticketRepository.findByParking_ParkingId(parkingId).forEach(tickets::add);
            if (isNull(parkingId) && !isNull(clientId))
                ticketRepository.findByClient_ClientId(clientId).forEach(tickets::add);
            else
                getAllTickets();
            return tickets
                    .stream()
                    .map(ticket -> ticketMapper.toDto(ticket))
                    .collect(Collectors.toList());
        }

        private List<TicketDto> getAllTickets() {
            List<Ticket> bills = new ArrayList<>();
            ticketRepository.findAll().forEach(bills::add);
            return bills
                    .stream()
                    .map(ticket -> ticketMapper.toDto(ticket))
                    .collect(Collectors.toList());
        }
*/
    List<TicketDto> getTicketsOfClient(UUID clientId) {
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findByClient_ClientId(clientId).forEach(ticket -> tickets.add(ticket));

        return tickets
                .stream()
                .map(ticket -> ticketMapper.toDto(ticket))
                .collect(Collectors.toList());
    }

    public TicketDto getTicketById(Long TicketId) {
        Ticket ticket = ticketRepository.findById(TicketId).orElseThrow(() -> new NotFoundException("Ticket not found "));
        return ticketMapper.toDto(ticket);
    }

    public TicketDto addTicket(TicketDto ticketDto) {
        UUID clientId = ticketDto.getClient().getClientId();
        //checkIfClientAtParking(clientId);

        Ticket ticket = ticketMapper.toModel(ticketDto);
        ticket.setClient(getClientById(clientId));
        ticket.setCarPark(getParkingById(ticketDto.getCarPark().getCarParkId()));

        Ticket addedTicket;
        //TicketDto checkTicket = getLastTicketOfClient(clientId);

        if (getTicketsOfClient(clientId).isEmpty()) {
            addedTicket = ticketRepository.save(ticket);
        } else if (getLastTicketOfClient(clientId).isClientAtParking()) {
            throw new ConflictException("Client already at parking");
        } else {
            addedTicket = ticketRepository.save(ticket);
        }

        return ticketMapper.toDto(addedTicket);
    }

    private void checkIfClientAtParking(UUID clientId) {
        Ticket checkTicket = ticketRepository.findTop1ByClient_ClientIdOrderByTicketIdDesc(clientId).orElseThrow(() -> new NotFoundException("Client not Found - ticket - 1"));
        if (checkTicket.isClientAtParking()) {
            throw new ConflictException("Client already at parking");
        }
    }

    public Client getClientById(UUID clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Client not Found - ticket - 2"));
    }

    private CarPark getParkingById(Long parkingId) {
        return carParkRepository.findById(parkingId).orElseThrow(() -> new NotFoundException("Parking not Found - ticket -3"));
    }


    public TicketDto updateTicket(TicketDto ticketDto, Long ticketId) {
        checkIfClientLeftParking(ticketDto.getClient().getClientId());
        Ticket ticket = ticketMapper.toModel(ticketDto);
        ticket.setTicketId(ticketId);
        ticket.setClient(getClientById(ticketDto.getClient().getClientId()));
        ticket.setEnteredParkingAt(getTicketById(ticketId).getEnteredParkingAt());
        //ticket.setClientAtParking(false);
        Ticket updatedTicket = ticketRepository.save(ticket);

        TicketDto returnDto = ticketMapper.toDto(updatedTicket);

        return returnDto;
    }

    private void checkIfClientLeftParking(UUID clientId) {
        Ticket checkTicket = ticketRepository.findTop1ByClient_ClientIdOrderByTicketIdDesc(clientId).orElseThrow(() -> new NotFoundException("Client not Found - ticket"));
        if (!checkTicket.isClientAtParking()) {
            throw new ConflictException("Client already left parking");
        }
    }

    public TicketDto getLastTicketOfClient(UUID clientId) {
        Ticket ticket = ticketRepository.findTop1ByClient_ClientIdOrderByTicketIdDesc(clientId).orElseThrow(() -> new NotFoundException("This client has no tickets "));
        //Optional<Ticket> ticket = ticketRepository.findTop1ByClient_ClientIdOrderByTicketIdDesc(clientId);

        logger.info("Ostatni ticket klienta : " + ticket);
        return ticketMapper.toDto(ticket);

    }
}
