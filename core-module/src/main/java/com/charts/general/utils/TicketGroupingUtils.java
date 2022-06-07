package com.charts.general.utils;

import com.charts.general.entity.ticket.updated.UpdateTicketEntity;
import com.charts.general.entity.ticket.updated.UpdateTicketList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketGroupingUtils {

    public static Map<String, List<UpdateTicketEntity>> groupByMonth(List<UpdateTicketEntity> ticketList) {
        return new UpdateTicketList(ticketList).getTicketEntities().stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getMonth));
    }

    public static Map<String, List<UpdateTicketEntity>> groupByTicketType(List<UpdateTicketEntity> couponEntityList) {
        return new UpdateTicketList(couponEntityList).getTicketEntities().stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getName));
    }

    public static Map<String, Object> groupByAndSumByTicketType(List<UpdateTicketEntity> entityList) {
        return new HashMap<>(entityList.stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getName, Collectors.summingLong(UpdateTicketEntity::getValue))));
    }

    public static Map<String, Object> groupByAndSumByMonth(List<UpdateTicketEntity> entityList) {
        return new HashMap<>(entityList.stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getMonth, Collectors.summingLong(UpdateTicketEntity::getValue))));
    }

}
