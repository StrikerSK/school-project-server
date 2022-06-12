package com.charts.general.entity.enums;

import com.charts.general.constants.TicketConstants;

public enum TicketTypes implements IEnum {

    FIFTEEN_MINUTES(TicketConstants.FIFTEEN_MINUTES, 1),
    ONE_DAY(TicketConstants.ONE_DAY, 2),
    ONE_DAY_ALL(TicketConstants.ONE_DAY_ALL, 3),
    TWO_ZONES(TicketConstants.TWO_ZONES, 4),
    THREE_ZONES(TicketConstants.THREE_ZONES, 5),
    FOUR_ZONES(TicketConstants.FOUR_ZONES, 6),
    FIVE_ZONES(TicketConstants.FIVE_ZONES, 7),
    SIX_ZONES(TicketConstants.SIX_ZONES, 8),
    SEVEN_ZONES(TicketConstants.SEVEN_ZONES, 9),
    EIGHT_ZONES(TicketConstants.EIGHT_ZONES, 10),
    NINE_ZONES(TicketConstants.NINE_ZONES, 11),
    TEN_ZONES(TicketConstants.TEN_ZONES, 12),
    ELEVEN_ZONES(TicketConstants.ELEVEN_ZONES, 13);

    private final String value;

    private final Integer orderValue;

    TicketTypes(String value, Integer orderValue) {
        this.value = value;
        this.orderValue = orderValue;
    }

    public String getValue() {
        return value;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

}
