package com.charts.general.utils;

import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.ticket.v2.TicketEntityV2;
import com.charts.general.entity.ticket.v2.TicketListV2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketGroupingUtils extends AbstractGroupingUtils{

    public static Map<TicketTypes, List<TicketEntityV2>> groupByTicketType(List<TicketEntityV2> couponEntityList) {
        return sortByOrderValue(new TicketListV2(couponEntityList).getTicketEntities().stream()
                .collect(Collectors.groupingBy(TicketEntityV2::getTicketType)));
    }

    public static Map<TicketTypes, Object> groupByAndSumByTicketType(List<TicketEntityV2> entityList) {
        return sortByOrderValue(new HashMap<>(entityList.stream()
                .collect(Collectors.groupingBy(TicketEntityV2::getTicketType, Collectors.summingInt(TicketEntityV2::getValue)))));
    }

    public static Map<Boolean, List<TicketEntityV2>> groupByDiscounted(List<TicketEntityV2> couponEntityList) {
        return new TicketListV2(couponEntityList).getTicketEntities().stream()
                .collect(Collectors.groupingBy(TicketEntityV2::getDiscounted));
    }

    public static Map<Boolean, Object> groupAndSumByDiscounted(List<TicketEntityV2> entityList) {
        return new HashMap<>(entityList.stream()
                .collect(Collectors.groupingBy(TicketEntityV2::getDiscounted, Collectors.summingInt(TicketEntityV2::getValue))));
    }

}
