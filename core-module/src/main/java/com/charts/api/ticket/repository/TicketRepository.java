package com.charts.api.ticket.repository;

import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.utils.TicketConverter;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TicketRepository {

    private final JpaTicketRepository jpaTicketRepository;

    @Cacheable("ticketList")
    public List<UpdateTicketEntity> getTicketList() {
        return TicketConverter.convertTicketEntity(jpaTicketRepository.findAll());
    }

}
