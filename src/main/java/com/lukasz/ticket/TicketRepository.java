package com.lukasz.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByClient_ClientId(UUID clientId);
    Optional<Ticket> findTop1ByClient_ClientIdOrderByTicketIdDesc(UUID clientId);
}