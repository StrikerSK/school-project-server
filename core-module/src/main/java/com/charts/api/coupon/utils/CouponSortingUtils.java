package com.charts.api.coupon.utils;

import com.charts.general.entity.enums.IEnum;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CouponSortingUtils {

    public static <R extends IEnum, T> Map<R, T> sortByOrderValue(Map<R, T> mapToSort) {
        return mapToSort.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(R::getOrderValue)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

}
