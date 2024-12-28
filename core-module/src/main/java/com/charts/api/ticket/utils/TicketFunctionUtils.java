package com.charts.api.ticket.utils;

import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.general.exception.InvalidParameterException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TicketFunctionUtils {

    public static final String MONTH_GROUP = "month";
    public static final String YEAR_GROUP = "year";
    public static final String DISCOUNTED_GROUP = "discounted";
    public static final String TICKET_GROUP = "ticket";

    public static void validateGroups(String upperGroup, String lowerGroup) {
        if (upperGroup.equals(lowerGroup)) {
            throw new InvalidParameterException("Cannot use same groups");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> createGrouping(String groupName) {
        Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> convertedData;

        switch (groupName.toLowerCase()) {
            case TICKET_GROUP:
                convertedData = (e) -> (Map<T, List<UpdateTicketEntity>>) TicketGroupingUtils.groupByTicketType(e);
                break;
            case MONTH_GROUP:
                convertedData = (e) -> (Map<T, List<UpdateTicketEntity>>) TicketGroupingUtils.groupByMonth(e);
                break;
            case DISCOUNTED_GROUP:
                convertedData = (e) -> (Map<T, List<UpdateTicketEntity>>) TicketGroupingUtils.groupByDiscounted(e);
                break;
            case YEAR_GROUP:
                convertedData = (e) -> (Map<T, List<UpdateTicketEntity>>) TicketGroupingUtils.groupByYear(e);
                break;
            default:
                throw new InvalidParameterException(
                        String.format("Unknown group name: %s! Available groups: %s, %s, %s, %s", groupName, YEAR_GROUP, MONTH_GROUP, TICKET_GROUP, DISCOUNTED_GROUP)
                );
        }

        return convertedData;
    }

}
