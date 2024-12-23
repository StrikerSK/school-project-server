package com.charts.general.utils;

import com.charts.general.entity.enums.IEnum;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractFilterUtils {

    protected static <T extends IEnum, R> List<R> filterByValue(List<R> coupons, List<T> values, Function<R, T> function) {
        return coupons.stream()
                .filter(coupon -> values.contains(function.apply(coupon)))
                .collect(Collectors.toList());
    }

}
