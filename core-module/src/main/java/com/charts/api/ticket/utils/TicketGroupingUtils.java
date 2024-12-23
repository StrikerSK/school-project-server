package com.charts.api.ticket.utils;

import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.general.utils.AbstractGroupingUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketGroupingUtils extends AbstractGroupingUtils {

    public static Map<Integer, List<UpdateTicketEntity>> groupByYear(List<UpdateTicketEntity> couponEntityList) {
        return couponEntityList.stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getYear));
    }

    public static Map<Months, List<UpdateTicketEntity>> groupByMonth(List<UpdateTicketEntity> entityList) {
        return groupValues(entityList, UpdateTicketEntity::getMonth);
    }

    public static Map<TicketTypes, List<UpdateTicketEntity>> groupByTicketType(List<UpdateTicketEntity> couponEntityList) {
        return groupValues(couponEntityList, UpdateTicketEntity::getTicketType);
    }

    public static Map<Boolean, List<UpdateTicketEntity>> groupByDiscounted(List<UpdateTicketEntity> couponEntityList) {
        return couponEntityList.stream()
                .collect(Collectors.groupingBy(UpdateTicketEntity::getDiscounted));
    }

    public static Map<Months, Object> groupAndSumByMonth(List<UpdateTicketEntity> entityList) {
        Map<Months, List<UpdateTicketEntity>> groupedValues = TicketGroupingUtils.groupByMonth(entityList);
        return TicketSortingUtils.sortByOrderValue(aggregateGroupsSum(groupedValues));
    }

    public static Map<TicketTypes, Object> groupByAndSumByTicketType(List<UpdateTicketEntity> entityList) {
        Map<TicketTypes, List<UpdateTicketEntity>> groupedValues = TicketGroupingUtils.groupByTicketType(entityList);
        return TicketSortingUtils.sortByOrderValue(aggregateGroupsSum(groupedValues));
    }

}
