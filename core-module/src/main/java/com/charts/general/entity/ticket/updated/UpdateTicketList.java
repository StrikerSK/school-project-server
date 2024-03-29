package com.charts.general.entity.ticket.updated;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.ticket.TicketEntity;
import com.charts.general.entity.ticket.TicketsParameters;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.charts.general.constants.TicketConstants.*;

@Getter
public class UpdateTicketList {

    private final List<UpdateTicketEntity> ticketEntities;

    public UpdateTicketList(TicketEntity ticketEntity) {
        ticketEntities = new ArrayList<>();
        this.fillData(ticketEntity);
    }

    public <T> UpdateTicketList(List<T> ticketList) {
        if (CollectionUtils.isNotEmpty(ticketList)) {
            T listItem = ticketList.get(0);
            if (listItem instanceof  TicketEntity) {
                this.ticketEntities = new ArrayList<>();
                ticketList.stream()
                        .map(TicketEntity.class::cast)
                        .forEach(this::fillData);
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

    private void fillData(TicketEntity ticketEntity) {
        ticketEntities.add(extractData(FIFTEEN_MINUTES, ticketEntity, TicketEntity::getFifteenMinutes));
        ticketEntities.add(extractData(ONE_DAY, ticketEntity, TicketEntity::getOneDay));
        ticketEntities.add(extractData(ONE_DAY_ALL, ticketEntity, TicketEntity::getOneDayAll));
        ticketEntities.add(extractData(TWO_ZONES, ticketEntity, TicketEntity::getTwoZones));
        ticketEntities.add(extractData(THREE_ZONES, ticketEntity, TicketEntity::getThreeZones));
        ticketEntities.add(extractData(FOUR_ZONES, ticketEntity, TicketEntity::getFourZones));
        ticketEntities.add(extractData(FIVE_ZONES, ticketEntity, TicketEntity::getFiveZones));
        ticketEntities.add(extractData(SIX_ZONES, ticketEntity, TicketEntity::getSixZones));
        ticketEntities.add(extractData(SEVEN_ZONES, ticketEntity, TicketEntity::getSevenZones));
        ticketEntities.add(extractData(EIGHT_ZONES, ticketEntity, TicketEntity::getEightZones));
        ticketEntities.add(extractData(NINE_ZONES, ticketEntity, TicketEntity::getNineZones));
        ticketEntities.add(extractData(TEN_ZONES, ticketEntity, TicketEntity::getTenZones));
        ticketEntities.add(extractData(ELEVEN_ZONES, ticketEntity, TicketEntity::getElevenZones));
    }

    private static UpdateTicketEntity extractData(String ticketType, TicketEntity ticketEntity, Function<TicketEntity, Long> function) {
        UpdateTicketEntity output = new UpdateTicketEntity();

        //From UpdateCouponEntity class
        output.setValue(function.apply(ticketEntity).intValue());
        output.setDiscounted(ticketEntity.getDiscounted());
        output.setTicketType(EnumUtils.getEnumValues(TicketTypes.class, ticketType).get());

        // From GeneralEntity class
        output.setMonth(ticketEntity.getMonth());
        output.setYear(ticketEntity.getYear());
        output.setCode(ticketEntity.getCode());

        return output;
    }

}
