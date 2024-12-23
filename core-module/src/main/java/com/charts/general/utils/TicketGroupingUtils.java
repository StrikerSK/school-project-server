package com.charts.general.utils;

import com.charts.general.entity.enums.TicketTypes;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketGroupingUtils extends AbstractGroupingUtils{

    public static Map<TicketTypes, List<UpdateTicketEntity>> groupByTicketType(List<UpdateTicketEntity> couponEntityList) {
        return sortByOrderValue(new UpdateTicketList(couponEntityList).getTicketEntities().stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getTicketType)));
    }

    public static Map<TicketTypes, Object> groupByAndSumByTicketType(List<UpdateTicketEntity> entityList) {
        return sortByOrderValue(new HashMap<>(entityList.stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getTicketType, Collectors.summingInt(UpdateTicketEntity::getValue)))));
    }

    public static Map<Boolean, List<UpdateTicketEntity>> groupByDiscounted(List<UpdateTicketEntity> couponEntityList) {
        return new UpdateTicketList(couponEntityList).getTicketEntities().stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getDiscounted));
    }

    public static Map<Boolean, Object> groupAndSumByDiscounted(List<UpdateTicketEntity> entityList) {
        return new HashMap<>(entityList.stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getDiscounted, Collectors.summingInt(UpdateTicketEntity::getValue))));
    }

}
