package com.charts.general.repository.ticket;

import com.charts.general.entity.ticket.v2.TicketEntityV2;
import com.charts.general.entity.ticket.v2.UpdateTicketList;
import com.charts.general.utils.TicketEntityConvertor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository {

    private final JpaTicketRepository jpaTicketRepository;

    public TicketRepository(JpaTicketRepository jpaTicketRepository) {
        this.jpaTicketRepository = jpaTicketRepository;
    }

    @Cacheable("ticketList")
    public UpdateTicketList getUpdatedTicketList() {
        List<TicketEntityV2> output = TicketEntityConvertor.convertList(jpaTicketRepository.findAll());
        return new UpdateTicketList(output);
    }

}
