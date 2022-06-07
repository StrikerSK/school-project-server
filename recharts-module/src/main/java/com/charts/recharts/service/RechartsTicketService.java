package com.charts.recharts.service;

import com.charts.general.entity.ticket.TicketsParameters;
import com.charts.general.entity.ticket.updated.UpdateTicketEntity;
import com.charts.general.entity.ticket.updated.UpdateTicketList;
import com.charts.general.repository.ticket.TicketRepository;
import com.charts.recharts.entity.RechartsDataObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RechartsTicketService {

    private final TicketRepository ticketsRepository;

    public RechartsTicketService(TicketRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    public List<List<RechartsDataObject>> getMonthlyDataByTicketType(TicketsParameters parameters) {
        UpdateTicketList couponList = ticketsRepository.getUpdatedTicketList().filterWithParameters(parameters);
        List<List<RechartsDataObject>> outputMapList = new ArrayList<>();
        parameters.getMonth().forEach(month -> {
            List<RechartsDataObject> nestedList = new ArrayList<>();
            UpdateTicketList entities = couponList.filterByMonth(Collections.singletonList(month));
            parameters.getTicketType().forEach(ticketType -> {
                Long monthlyValue = entities.filterByTicketType(Collections.singletonList(ticketType)).getTicketEntities().stream()
                        .map(UpdateTicketEntity::getValue)
                        .reduce(0L, Long::sum);
                nestedList.add(new RechartsDataObject(month, ticketType, monthlyValue));
            });
            outputMapList.add(nestedList);
        });
        return outputMapList;
    }

}
