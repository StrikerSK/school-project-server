package com.charts.api.ticket.utils;

import com.charts.general.entity.enums.types.EnumProxy;
import com.charts.general.entity.enums.types.Months;
import com.charts.api.ticket.enums.TicketType;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.general.utils.AbstractGroupingUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketGroupingUtils extends AbstractGroupingUtils {

    public static Map<TicketType, List<UpdateTicketEntity>> groupByTicketType(List<UpdateTicketEntity> couponEntityList) {
        return groupValues(couponEntityList, UpdateTicketEntity::getTicketType);
    }

    public static Map<EnumProxy, List<UpdateTicketEntity>> groupByDiscounted(List<UpdateTicketEntity> couponEntityList) {
        return couponEntityList.stream()
                .collect(Collectors.groupingBy(e -> new EnumProxy(e.getDiscounted().toString(), 0)));
    }

    public static Map<Months, Object> groupAndSumByMonth(List<UpdateTicketEntity> entityList) {
        Map<Months, List<UpdateTicketEntity>> groupedValues = TicketGroupingUtils.groupByMonth(entityList);
        return TicketSortingUtils.sortByOrderValue(aggregateGroupsSum(groupedValues));
    }

    public static Map<TicketType, Object> groupByAndSumByTicketType(List<UpdateTicketEntity> entityList) {
        Map<TicketType, List<UpdateTicketEntity>> groupedValues = TicketGroupingUtils.groupByTicketType(entityList);
        return TicketSortingUtils.sortByOrderValue(aggregateGroupsSum(groupedValues));
    }

}
