package com.charts.api.ticket.entity.v2;

import com.charts.api.ticket.utils.TicketFilterUtils;
import com.charts.general.entity.enums.Months;
import com.charts.api.ticket.enums.TicketType;
import com.charts.api.ticket.entity.v1.TicketEntity;
import com.charts.general.entity.parameters.TicketsParameters;
import com.charts.api.ticket.utils.TicketConverter;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UpdateTicketList {

    private final List<UpdateTicketEntity> ticketEntities;

    public UpdateTicketList(TicketEntity ticketEntity) {
        ticketEntities = TicketConverter.convertTicketEntity(ticketEntity);
    }

    public <T> UpdateTicketList(List<T> ticketList) {
        if (CollectionUtils.isNotEmpty(ticketList)) {
            T listItem = ticketList.get(0);
            if (listItem instanceof  TicketEntity) {
                this.ticketEntities = ticketList.stream()
                        .map(TicketEntity.class::cast)
                        .map(TicketConverter::convertTicketEntity)
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
            } else if (listItem instanceof UpdateTicketEntity) {
                this.ticketEntities = ticketList.stream()
                        .map(UpdateTicketEntity.class::cast)
                        .collect(Collectors.toList());
            } else {
                throw new IllegalArgumentException("Type cannot be determined!");
            }
        } else {
            ticketEntities = new ArrayList<>();
        }
    }
    public UpdateTicketList filterByMonth(List<Months> months) {
        return new UpdateTicketList(TicketFilterUtils.filterByMonth(ticketEntities, months));
    }

    public UpdateTicketList filterByYear(List<Integer> years) {
        return new UpdateTicketList(TicketFilterUtils.filterByYear(ticketEntities, years));
    }

    public UpdateTicketList filterByDiscounted(List<Boolean> discounted) {
        return new UpdateTicketList(TicketFilterUtils.filterByDiscounted(ticketEntities, discounted));
    }

    public UpdateTicketList filterByTicketType(List<TicketType> ticketTypes) {
        return new UpdateTicketList(TicketFilterUtils.filterByTypes(ticketEntities, ticketTypes));
    }

    public UpdateTicketList filterWithParameters(TicketsParameters parameters) {
        return filterByYear(parameters.getYearInteger())
                .filterByDiscounted(parameters.getDiscounted())
                .filterByTicketType(parameters.getTicketType())
                .filterByMonth(parameters.getMonths());
    }

}
