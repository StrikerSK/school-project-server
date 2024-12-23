package com.charts.api.ticket.utils;

import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.utils.AbstractFilterUtils;

import java.util.List;

public class TicketFilterUtils extends AbstractFilterUtils {

    public static List<UpdateTicketEntity> filterByMonth(List<UpdateTicketEntity> coupons, List<Months> values) {
        return filterByValue(coupons, values, UpdateTicketEntity::getMonth);
    }

    public static List<UpdateTicketEntity> filterByTypes(List<UpdateTicketEntity> coupons, List<TicketTypes> values) {
        return filterByValue(coupons, values, UpdateTicketEntity::getTicketType);
    }

}
