package com.charts.general.entity.ticket.v2;

import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.ticket.v1.TicketEntityV1;
import com.charts.general.entity.ticket.TicketsParameters;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.charts.general.constants.TicketConstants.*;

@Getter
public class TicketListV2 {

    private final List<TicketEntityV2> ticketEntities;

    public TicketListV2(TicketEntityV1 ticketEntity) {
        ticketEntities = new ArrayList<>();
        this.fillData(ticketEntity);
    }

    public <T> TicketListV2(List<T> ticketList) {
        if (CollectionUtils.isNotEmpty(ticketList)) {
            T listItem = ticketList.get(0);
            if (listItem instanceof TicketEntityV1) {
                this.ticketEntities = new ArrayList<>();
                ticketList.stream()
                        .map(TicketEntityV1.class::cast)
                        .forEach(this::fillData);
            } else if (listItem instanceof TicketEntityV2) {
                this.ticketEntities = ticketList.stream()
                        .map(TicketEntityV2.class::cast)
                        .collect(Collectors.toList());
            } else {
                throw new IllegalArgumentException("Type cannot be determined!");
            }
        } else {
            ticketEntities = new ArrayList<>();
        }
    }
    public TicketListV2 filterByMonth(List<Months> months) {
        return new TicketListV2(ticketEntities.stream()
                .filter(e -> months.contains(e.getMonth()))
                .collect(Collectors.toList()));
    }

    public TicketListV2 filterByYear(List<Integer> years) {
        return new TicketListV2(ticketEntities.stream()
                .filter(e -> years.contains(e.getYear()))
                .collect(Collectors.toList()));
    }

    public TicketListV2 filterByDiscounted(List<Boolean> discounted) {
        return new TicketListV2(ticketEntities.stream()
                .filter(e -> discounted.contains(e.getDiscounted()))
                .collect(Collectors.toList()));
    }

    public TicketListV2 filterByTicketType(List<TicketTypes> ticketTypes) {
        return new TicketListV2(ticketEntities.stream()
                .filter(e -> ticketTypes.contains(e.getTicketType()))
                .collect(Collectors.toList()));
    }

    public TicketListV2 filterWithParameters(TicketsParameters parameters) {
        return filterByYear(parameters.getYearInteger())
                .filterByDiscounted(parameters.getDiscounted())
                .filterByTicketType(parameters.getTicketType())
                .filterByMonth(parameters.getMonths());
    }

    private void fillData(TicketEntityV1 ticketEntity) {
        Stream.of(
                extractData(FIFTEEN_MINUTES, ticketEntity, TicketEntityV1::getFifteenMinutes),
                extractData(ONE_DAY, ticketEntity, TicketEntityV1::getOneDay),
                extractData(ONE_DAY_ALL, ticketEntity, TicketEntityV1::getOneDayAll),
                extractData(TWO_ZONES, ticketEntity, TicketEntityV1::getTwoZones),
                extractData(THREE_ZONES, ticketEntity, TicketEntityV1::getThreeZones),
                extractData(FOUR_ZONES, ticketEntity, TicketEntityV1::getFourZones),
                extractData(FIVE_ZONES, ticketEntity, TicketEntityV1::getFiveZones),
                extractData(SIX_ZONES, ticketEntity, TicketEntityV1::getSixZones),
                extractData(SEVEN_ZONES, ticketEntity, TicketEntityV1::getSevenZones),
                extractData(EIGHT_ZONES, ticketEntity, TicketEntityV1::getEightZones),
                extractData(NINE_ZONES, ticketEntity, TicketEntityV1::getNineZones),
                extractData(TEN_ZONES, ticketEntity, TicketEntityV1::getTenZones),
                extractData(ELEVEN_ZONES, ticketEntity, TicketEntityV1::getElevenZones)
        ).forEach(ticketEntities::add);
    }

    private static TicketEntityV2 extractData(String ticketType, TicketEntityV1 ticketEntity, Function<TicketEntityV1, Long> function) {
        TicketEntityV2 output = new TicketEntityV2();

        //From UpdateCouponEntity class
        output.setValue(function.apply(ticketEntity).intValue());
        output.setDiscounted(ticketEntity.getDiscounted());
        output.setTicketType(TicketTypes.getType(ticketType).get());

        // From GeneralEntity class
        output.setMonth(ticketEntity.getMonth());
        output.setYear(ticketEntity.getYear());
        output.setCode(ticketEntity.getCode());

        return output;
    }

}
