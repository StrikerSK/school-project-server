package com.charts.api.ticket.constants;

import com.charts.general.entity.enums.types.EnumAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TicketConstants {

    // Ticket types values
    public static final String FIFTEEN_MINUTES = "15 Minútové";
    public static final String ONE_DAY = "24 Hodinový - Pásmo P";
    public static final String ONE_DAY_ALL = "24 Hodinový - Všetky pásma";
    public static final String TWO_ZONES = "2 Pásma";
    public static final String THREE_ZONES = "3 Pásma";
    public static final String FOUR_ZONES = "4 Pásma";
    public static final String  FIVE_ZONES = "5 Pásiem";
    public static final String SIX_ZONES = "6 Pásiem";
    public static final String SEVEN_ZONES = "7 Pásiem";
    public static final String EIGHT_ZONES = "8 Pásiem";
    public static final String NINE_ZONES = "9 Pásiem";
    public static final String TEN_ZONES = "10 Pásiem";
    public static final String ELEVEN_ZONES = "11 Pásiem";

    public static final List<Boolean> DISCOUNTED_LIST = Arrays.asList(true, false);
    public static final List<EnumAdapter> DISCOUNTED_VALUES = DISCOUNTED_LIST.stream().map(EnumAdapter::new).collect(Collectors.toList());

}
