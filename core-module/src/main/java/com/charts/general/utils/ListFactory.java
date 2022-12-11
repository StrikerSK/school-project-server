package com.charts.general.utils;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.coupon.v2.CouponEntityV2;
import com.charts.general.entity.coupon.v2.CouponListV2;
import com.charts.general.entity.ticket.v2.TicketEntityV2;
import com.charts.general.entity.ticket.v2.TicketListV2;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ListFactory {

    public static <T extends AbstractUpdateEntity> List<? extends AbstractUpdateEntity> getList(List<T> entityList) {
        if (CollectionUtils.isNotEmpty(entityList)) {
            Class<?> clazz = entityList.get(0).getClass();
            if (clazz == null) {
                throw new NullPointerException("Class type not provided");
            } else if (clazz == CouponEntityV2.class) {
                return new CouponListV2(entityList).getCouponEntityList();
            } else if (clazz == TicketEntityV2.class) {
                return new TicketListV2(entityList).getTicketEntities();
            }
        }
        return new ArrayList<>();
    }

}
