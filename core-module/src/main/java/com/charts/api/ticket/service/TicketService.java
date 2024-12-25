package com.charts.api.ticket.service;

import com.charts.general.entity.GroupingEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.repository.JpaTicketV2Repository;
import com.charts.api.ticket.enums.TicketType;
import com.charts.api.ticket.entity.TicketsParameters;
import com.charts.general.entity.enums.types.EnumProxy;
import com.charts.general.entity.enums.types.Months;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<GroupingEntity<TicketType>> getTicketsByTicketType(TicketsParameters parameters) {
        return ticketRepository.findGroupedByTicketType(
                parameters.getMonths(),
                parameters.getDiscounted(),
                parameters.getTicketType(),
                parameters.getYearInteger()
        );
    }

    public List<GroupingEntity<Months>> getTicketsByMonth(TicketsParameters parameters) {
        return ticketRepository.findGroupedByMonth(
                parameters.getMonths(),
                parameters.getDiscounted(),
                parameters.getTicketType(),
                parameters.getYearInteger()
        );
    }

    public List<GroupingEntity<EnumProxy>> getTicketsByYear(TicketsParameters parameters) {
        return ticketRepository.findGroupedByYear(
                parameters.getMonths(),
                parameters.getDiscounted(),
                parameters.getTicketType(),
                parameters.getYearInteger()
        )
                .stream()
                .map(e -> new GroupingEntity<>(new EnumProxy(e.getKey()), e.getValue()))
                .collect(Collectors.toList());
    }

    public List<GroupingEntity<EnumProxy>> getTicketsByDiscounted(TicketsParameters parameters) {
        return ticketRepository.findGroupedByDiscounted(
                parameters.getMonths(),
                parameters.getDiscounted(),
                parameters.getTicketType(),
                parameters.getYearInteger()
        )
                .stream()
                .map(e -> new GroupingEntity<>(new EnumProxy(e.getKey().toString(), 0), e.getValue()))
                .collect(Collectors.toList());
    }

}
