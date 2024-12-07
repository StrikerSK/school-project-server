package com.charts.general.utils.converter;

import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.ticket.TicketEntity;
import com.charts.general.entity.ticket.updated.UpdateTicketEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TicketConverter {

    public static List<UpdateTicketEntity> convertTicketEntity(TicketEntity ticketEntities) {
        return fillData(ticketEntities);
    }

    public static List<UpdateTicketEntity> convertTicketEntities(List<TicketEntity> ticketEntities) {
        return ticketEntities.stream()
                .map(TicketConverter::fillData)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<UpdateTicketEntity> fillData(TicketEntity ticketEntity) {
        List<UpdateTicketEntity> ticketEntities = new ArrayList<>();
        List<TicketTypes> ticketTypes = Arrays.asList(
                TicketTypes.FIFTEEN_MINUTES,
                TicketTypes.ONE_DAY,
                TicketTypes.ONE_DAY_ALL,
                TicketTypes.TWO_ZONES,
                TicketTypes.THREE_ZONES,
                TicketTypes.FOUR_ZONES,
                TicketTypes.FIVE_ZONES,
                TicketTypes.SIX_ZONES,
                TicketTypes.SEVEN_ZONES,
                TicketTypes.EIGHT_ZONES,
                TicketTypes.NINE_ZONES,
                TicketTypes.TEN_ZONES,
                TicketTypes.ELEVEN_ZONES
        );

        ticketTypes.forEach(ticketType -> ticketEntities.add(extractData(ticketType.getValue(), ticketEntity, getFunction(ticketType))));
        return ticketEntities;
    }

    private static Function<TicketEntity, Long> getFunction(TicketTypes ticketType) {
        switch (ticketType) {
            case FIFTEEN_MINUTES:
                return TicketEntity::getFifteenMinutes;
            case ONE_DAY:
                return TicketEntity::getOneDay;
            case ONE_DAY_ALL:
                return TicketEntity::getOneDayAll;
            case TWO_ZONES:
                return TicketEntity::getTwoZones;
            case THREE_ZONES:
                return TicketEntity::getThreeZones;
            case FOUR_ZONES:
                return TicketEntity::getFourZones;
            case FIVE_ZONES:
                return TicketEntity::getFiveZones;
            case SIX_ZONES:
                return TicketEntity::getSixZones;
            case SEVEN_ZONES:
                return TicketEntity::getSevenZones;
            case EIGHT_ZONES:
                return TicketEntity::getEightZones;
            case NINE_ZONES:
                return TicketEntity::getNineZones;
            case TEN_ZONES:
                return TicketEntity::getTenZones;
            case ELEVEN_ZONES:
                return TicketEntity::getElevenZones;
            default:
                throw new IllegalArgumentException("Invalid ticketType");
        }
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
