package com.charts.general.utils;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.entity.v2.UpdateCouponList;
import com.charts.general.entity.ticket.updated.UpdateTicketEntity;
import com.charts.general.entity.ticket.updated.UpdateTicketList;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ListFactory {

    public static <T extends AbstractUpdateEntity> List<? extends AbstractUpdateEntity> getList(List<T> entityList) {
        if (CollectionUtils.isNotEmpty(entityList)) {
            Class<?> clazz = entityList.get(0).getClass();
            if (clazz == null) {
                throw new NullPointerException("Class type not provided");
            } else if (clazz == UpdateCouponEntity.class) {
                return new UpdateCouponList(entityList).getCouponEntityList();
            } else if (clazz == UpdateTicketEntity.class) {
                return new UpdateTicketList(entityList).getTicketEntities();
            }
        }
        return new ArrayList<>();
    }

}
