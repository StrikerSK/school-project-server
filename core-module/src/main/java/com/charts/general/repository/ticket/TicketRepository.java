package com.charts.general.repository.ticket;

import com.charts.general.entity.ticket.v2.TicketListV2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {

    private final JpaTicketRepository jpaTicketRepository;

    public TicketRepository(JpaTicketRepository jpaTicketRepository) {
        this.jpaTicketRepository = jpaTicketRepository;
    }

    @Cacheable("ticketList")
    public TicketListV2 getUpdatedTicketList() {
        return new TicketListV2(jpaTicketRepository.findAll());
    }

}
