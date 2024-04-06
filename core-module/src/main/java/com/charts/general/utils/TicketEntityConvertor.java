package com.charts.general.utils;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.ticket.v1.TicketEntity;
import com.charts.general.entity.ticket.v2.TicketEntityV2;

import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.charts.general.constants.TicketConstants.*;

public class TicketEntityConvertor {

    public static List<TicketEntityV2> convertList(List<TicketEntity> list) {
        return list.stream()
                .flatMap(e -> fillData(e).stream())
                .collect(Collectors.toList());
    }

    public static List<TicketEntityV2> fillData(TicketEntity ticketEntity) {
        return Stream.of(
                new AbstractMap.SimpleEntry<>(FIFTEEN_MINUTES, ticketEntity.getFifteenMinutes()),
                new AbstractMap.SimpleEntry<>(ONE_DAY, ticketEntity.getOneDay()),
                new AbstractMap.SimpleEntry<>(ONE_DAY_ALL, ticketEntity.getOneDayAll()),
                new AbstractMap.SimpleEntry<>(TWO_ZONES, ticketEntity.getTwoZones()),
                new AbstractMap.SimpleEntry<>(THREE_ZONES, ticketEntity.getThreeZones()),
                new AbstractMap.SimpleEntry<>(FOUR_ZONES, ticketEntity.getFourZones()),
                new AbstractMap.SimpleEntry<>(FIVE_ZONES, ticketEntity.getFiveZones()),
                new AbstractMap.SimpleEntry<>(SIX_ZONES, ticketEntity.getSixZones()),
                new AbstractMap.SimpleEntry<>(SEVEN_ZONES, ticketEntity.getSevenZones()),
                new AbstractMap.SimpleEntry<>(EIGHT_ZONES, ticketEntity.getEightZones()),
                new AbstractMap.SimpleEntry<>(NINE_ZONES, ticketEntity.getNineZones()),
                new AbstractMap.SimpleEntry<>(TEN_ZONES, ticketEntity.getTenZones()),
                new AbstractMap.SimpleEntry<>(ELEVEN_ZONES, ticketEntity.getElevenZones())
        ).map(entry -> extractData(entry.getKey(), ticketEntity, entry.getValue())).collect(Collectors.toList());
    }

    private static TicketEntityV2 extractData(String ticketType, TicketEntity ticketEntity, Long value) {
        TicketEntityV2 output = new TicketEntityV2();

        //From UpdateCouponEntity class
        output.setValue(value.intValue());
        output.setDiscounted(ticketEntity.getDiscounted());
        output.setTicketType(EnumUtils.getEnumValues(TicketTypes.class, ticketType).get());

        // From GeneralEntity class
        output.setMonth(ticketEntity.getMonth());
        output.setYear(ticketEntity.getYear());
        output.setCode(ticketEntity.getCode());

        return output;
    }

}
