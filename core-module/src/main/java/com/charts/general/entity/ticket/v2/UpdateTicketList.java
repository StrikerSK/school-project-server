package com.charts.general.entity.ticket.v2;

import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.ticket.v1.TicketEntity;
import com.charts.general.entity.ticket.v1.TicketsParameters;
import com.charts.general.utils.TicketEntityConvertor;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UpdateTicketList {

    private final List<TicketEntityV2> ticketEntities;

    public <T> UpdateTicketList(List<T> ticketList) {
        if (CollectionUtils.isNotEmpty(ticketList)) {
            T listItem = ticketList.get(0);
            if (listItem instanceof  TicketEntity) {
                this.ticketEntities = convertList(ticketList);
            } else if (listItem instanceof TicketEntityV2) {
                this.ticketEntities = castList(ticketList);
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

    private static <T> List<TicketEntityV2> convertList(List<T> list) {
        return list.stream()
                .map(TicketEntity.class::cast)
                .flatMap(e -> TicketEntityConvertor.fillData(e).stream())
                .collect(Collectors.toList());
    }

    private static <T> List<TicketEntityV2> castList(List<T> list) {
        return list.stream()
                .map(TicketEntityV2.class::cast)
                .collect(Collectors.toList());
    }

}
