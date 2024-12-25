package com.charts.api.ticket.service;

import com.charts.general.entity.GroupingEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.repository.JpaTicketV2Repository;
import com.charts.api.ticket.enums.TicketType;
import com.charts.api.ticket.entity.TicketsParameters;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final JpaTicketV2Repository ticketRepository;

    public TicketService(JpaTicketV2Repository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<UpdateTicketEntity> getAllTickets() { return ticketRepository.findAll(); }

    public List<UpdateTicketEntity> getAllByFilter(TicketsParameters parameters) {
        return ticketRepository.findAllByMonthInAndYearInAndTicketTypeInAndDiscountedIn(
                parameters.getMonths(),
                parameters.getYearInteger(),
                parameters.getTicketType(),
                parameters.getDiscounted()
        );
    }

    public List<GroupingEntity<TicketType>> getTicketTypesByMonth(TicketsParameters parameters) {
        return ticketRepository.findGroupedByTicketType(
                parameters.getMonths(),
                parameters.getDiscounted(),
                parameters.getTicketType(),
                parameters.getYearInteger()
        );
    }

}
