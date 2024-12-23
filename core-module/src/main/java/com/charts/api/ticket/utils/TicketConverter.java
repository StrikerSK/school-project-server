package com.charts.api.ticket.utils;

import com.charts.general.entity.enums.TicketTypes;
import com.charts.api.ticket.entity.v1.TicketEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.charts.general.constants.TicketConstants.*;

public class TicketConverter {

    public static List<UpdateTicketEntity> convertTicketEntity(TicketEntity ticketEntities) {
        return fillData(ticketEntities);
    }

    public static List<UpdateTicketEntity> convertTicketEntity(List<TicketEntity> ticketEntities) {
        return ticketEntities.stream()
                .map(TicketConverter::fillData)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<UpdateTicketEntity> fillData(TicketEntity ticketEntity) {
        List<UpdateTicketEntity> ticketEntities = new ArrayList<>();
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
        return ticketEntities;
    }

    private static UpdateTicketEntity extractData(String ticketType, TicketEntity ticketEntity, Function<TicketEntity, Long> function) {
        UpdateTicketEntity output = new UpdateTicketEntity();

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
