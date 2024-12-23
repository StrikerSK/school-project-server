package com.charts.api.ticket.utils;

import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketList;
import com.charts.general.utils.AbstractGroupingUtils;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketGroupingUtils extends AbstractGroupingUtils {

    public static Map<Months, List<UpdateTicketEntity>> groupByMonth(List<UpdateTicketEntity> entityList) {
        return groupValues(entityList, UpdateTicketEntity::getMonth);
    }

    public static Map<TicketTypes, List<UpdateTicketEntity>> groupByTicketType(List<UpdateTicketEntity> couponEntityList) {
        return groupValues(couponEntityList, UpdateTicketEntity::getTicketType);
    }

    public static Map<Boolean, List<UpdateTicketEntity>> groupByDiscounted(List<UpdateTicketEntity> couponEntityList) {
        return new UpdateTicketList(couponEntityList).getTicketEntities().stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getDiscounted));
    }

    public static Map<TicketTypes, Object> groupByAndSumByTicketType(List<UpdateTicketEntity> entityList) {
        return sortByOrderValue(new HashMap<>(entityList.stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getTicketType, Collectors.summingInt(UpdateTicketEntity::getValue)))));
    }

    public static <T extends IEnum> Map<T, Long> sumGroup(Map<T, List<UpdateTicketEntity>> entityList) {
        return entityList.entrySet()
                .stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), sumGroup(e.getValue()).longValue()))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

    }

    public static Integer sumGroup(List<UpdateTicketEntity> entityList) {
        return entityList.stream().mapToInt(UpdateTicketEntity::getValue).sum();
    }

}
