package com.charts.general.entity.ticket.updated;

import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.ticket.TicketEntity;
import com.charts.general.entity.parameters.TicketsParameters;
import com.charts.general.utils.converter.TicketConverter;
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
        return new UpdateTicketList(ticketEntities.stream()
                .filter(e -> months.contains(e.getMonth()))
                .collect(Collectors.toList()));
    }

    public UpdateTicketList filterByYear(List<Integer> years) {
        return new UpdateTicketList(ticketEntities.stream()
                .filter(e -> years.contains(e.getYear()))
                .collect(Collectors.toList()));
    }

    public UpdateTicketList filterByDiscounted(List<Boolean> discounted) {
        return new UpdateTicketList(ticketEntities.stream()
                .filter(e -> discounted.contains(e.getDiscounted()))
                .collect(Collectors.toList()));
    }

    public UpdateTicketList filterByTicketType(List<TicketTypes> ticketTypes) {
        return new UpdateTicketList(ticketEntities.stream()
                .filter(e -> ticketTypes.contains(e.getTicketType()))
                .collect(Collectors.toList()));
    }

    public UpdateTicketList filterWithParameters(TicketsParameters parameters) {
        return filterByYear(parameters.getYearInteger())
                .filterByDiscounted(parameters.getDiscounted())
                .filterByTicketType(parameters.getTicketType())
                .filterByMonth(parameters.getMonths());
    }

}
