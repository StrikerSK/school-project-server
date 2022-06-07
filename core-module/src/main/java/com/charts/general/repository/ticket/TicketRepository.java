package com.charts.general.repository.ticket;

import com.charts.general.entity.ticket.updated.UpdateTicketList;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {

    private final JpaTicketRepository jpaTicketRepository;

    public TicketRepository(JpaTicketRepository jpaTicketRepository) {
        this.jpaTicketRepository = jpaTicketRepository;
    }

    public UpdateTicketList getUpdatedTicketList() {
        return new UpdateTicketList(jpaTicketRepository.findAll());
    }

}
