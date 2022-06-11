package com.charts.general.entity.enums;

import com.charts.general.constants.TicketConstants;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum TicketTypes implements IEnum {

    FIFTEEN_MINUTES(TicketConstants.FIFTEEN_MINUTES),
    ONE_DAY(TicketConstants.ONE_DAY),
    ONE_DAY_ALL(TicketConstants.ONE_DAY_ALL),
    TWO_ZONES(TicketConstants.TWO_ZONES),
    THREE_ZONES(TicketConstants.THREE_ZONES),
    FOUR_ZONES(TicketConstants.FOUR_ZONES),
    FIVE_ZONES(TicketConstants.FIVE_ZONES),
    SIX_ZONES(TicketConstants.SIX_ZONES),
    SEVEN_ZONES(TicketConstants.SEVEN_ZONES),
    EIGHT_ZONES(TicketConstants.EIGHT_ZONES),
    NINE_ZONES(TicketConstants.NINE_ZONES),
    TEN_ZONES(TicketConstants.TEN_ZONES),
    ELEVEN_ZONES(TicketConstants.ELEVEN_ZONES);

    private final String value;

    TicketTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
