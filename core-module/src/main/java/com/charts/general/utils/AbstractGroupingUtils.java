package com.charts.general.utils;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.coupon.updated.UpdateCouponEntity;
import com.charts.general.entity.coupon.updated.UpdateCouponList;
import com.charts.general.entity.ticket.updated.UpdateTicketEntity;
import com.charts.general.entity.ticket.updated.UpdateTicketList;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractGroupingUtils {

    protected static <T extends AbstractUpdateEntity> List<? extends AbstractUpdateEntity> getList(List<T> entityList) {
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

    public static <T extends AbstractUpdateEntity> Map<String, List<T>> groupByCode(List<T> entityList) {
        return getList(entityList).stream().map(e -> (T) e).collect(Collectors.groupingBy(T::getCode));
    }

    public static <T extends AbstractUpdateEntity> Map<String, Object> groupAndSumByCode(List<T> entityList) {
        return new HashMap<>(entityList.stream().collect(Collectors.groupingBy(T::getCode, Collectors.summingInt(T::getValue))));
    }

    public static <T extends AbstractUpdateEntity> Map<String, List<T>> groupByMonth(List<T> entityList) {
        return getList(entityList).stream().map(e -> (T) e).collect(Collectors.groupingBy(T::getMonth));
    }

    public static <T extends AbstractUpdateEntity> Map<String, Object> groupAndSumByMonth(List<T> entityList) {
        return new HashMap<>(entityList.stream().collect(Collectors.groupingBy(T::getMonth, Collectors.summingInt(T::getValue))));
    }

    public static <T extends AbstractUpdateEntity> Map<Integer, List<T>> groupByYear(List<T> entityList) {
        return getList(entityList).stream().map(e -> (T) e).collect(Collectors.groupingBy(T::getYear));
    }

    public static <T extends AbstractUpdateEntity> Map<Integer, Object> groupAndSumByYear(List<T> entityList) {
        return new HashMap<>(entityList.stream().collect(Collectors.groupingBy(T::getYear, Collectors.summingInt(T::getValue))));
    }

}
