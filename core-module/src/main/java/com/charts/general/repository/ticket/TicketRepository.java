package com.charts.general.repository.ticket;

import com.charts.general.entity.ticket.v2.UpdateTicketList;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {

    private final JpaTicketRepository jpaTicketRepository;

    public TicketRepository(JpaTicketRepository jpaTicketRepository) {
        this.jpaTicketRepository = jpaTicketRepository;
    }

    @Cacheable("ticketList")
    public UpdateTicketList getUpdatedTicketList() {
        return new UpdateTicketList(jpaTicketRepository.findAll());
    }

}
