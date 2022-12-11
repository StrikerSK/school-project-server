package com.charts.recharts.service;

import com.charts.general.entity.ticket.TicketsParameters;
import com.charts.general.entity.ticket.v2.TicketEntityV2;
import com.charts.general.entity.ticket.v2.TicketListV2;
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
        TicketListV2 couponList = ticketsRepository.getUpdatedTicketList().filterWithParameters(parameters);
        List<List<RechartsDataObject>> outputMapList = new ArrayList<>();
        parameters.getMonths().forEach(month -> {
            List<RechartsDataObject> nestedList = new ArrayList<>();
            TicketListV2 entities = couponList.filterByMonth(Collections.singletonList(month));
            parameters.getTicketType().forEach(ticketType -> {
                Integer monthlyValue = entities.filterByTicketType(Collections.singletonList(ticketType)).getTicketEntities().stream()
                        .map(TicketEntityV2::getValue)
                        .reduce(0, Integer::sum);
                nestedList.add(new RechartsDataObject(ticketType, month, monthlyValue));
            });
            outputMapList.add(nestedList);
        });
        return outputMapList;
    }

}
