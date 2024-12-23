package com.charts.recharts.service;

import com.charts.general.entity.parameters.TicketsParameters;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketList;
import com.charts.api.ticket.repository.TicketRepository;
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
        parameters.getMonths().forEach(month -> {
            List<RechartsDataObject> nestedList = new ArrayList<>();
            UpdateTicketList entities = couponList.filterByMonth(Collections.singletonList(month));
            parameters.getTicketType().forEach(ticketType -> {
                Integer monthlyValue = entities.filterByTicketType(Collections.singletonList(ticketType)).getTicketEntities().stream()
                        .map(UpdateTicketEntity::getValue)
                        .reduce(0, Integer::sum);
                nestedList.add(new RechartsDataObject(ticketType, month, monthlyValue));
            });
            outputMapList.add(nestedList);
        });
        return outputMapList;
    }

}
