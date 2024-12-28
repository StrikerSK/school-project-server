package com.charts.recharts.service;

import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.utils.CouponFunctionUtils;
import com.charts.api.coupon.utils.CouponGroupingUtils;
import com.charts.api.ticket.entity.TicketsParameters;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketList;
import com.charts.api.ticket.repository.TicketRepository;
import com.charts.api.ticket.service.TicketService;
import com.charts.api.ticket.utils.TicketFunctionUtils;
import com.charts.api.ticket.utils.TicketGroupingUtils;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.utils.AbstractGroupingUtils;
import com.charts.recharts.entity.RechartsDataObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RechartsTicketService {

    private final TicketService ticketService;

    public <T extends IEnum> List<List<RechartsDataObject>> getTicketData(
            String upperGroup,
            String lowerGroup,
            TicketsParameters parameters
    ) {
        TicketFunctionUtils.validateGroups(upperGroup, lowerGroup);

        Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> upperFunction = TicketFunctionUtils.createGrouping(upperGroup);
        Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> lowerFunction = TicketFunctionUtils.createGrouping(lowerGroup);

        List<UpdateTicketEntity> couponList = ticketService.getAllByFilter(parameters);
        return upperFunction.apply(couponList)
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getKey().getOrderValue()))
                .map(upper -> {
                    Map<T, List<UpdateTicketEntity>> nestedGrouping = lowerFunction.apply(upper.getValue());
                    return AbstractGroupingUtils.aggregateGroupsSum(nestedGrouping).entrySet()
                            .stream()
                            .sorted(Comparator.comparing(e -> e.getKey().getOrderValue()))
                            .map(lower -> new RechartsDataObject(upper.getKey(), lower.getKey(), (int) lower.getValue()))
                            .collect(Collectors.toList());
                })
                .collect(Collectors.toList());
    }

}
