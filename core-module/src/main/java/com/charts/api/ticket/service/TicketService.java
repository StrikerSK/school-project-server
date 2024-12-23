package com.charts.api.ticket.service;

import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.repository.JpaTicketV2Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final JpaTicketV2Repository ticketRepository;

    public TicketService(JpaTicketV2Repository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<UpdateTicketEntity> getAllTickets() { return ticketRepository.findAll(); }

}
