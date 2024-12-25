package com.charts.general.utils;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.types.Months;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractFilterUtils {

    public static <T extends AbstractUpdateEntity> List<T> filterByMonth(List<T> coupons, List<Months> values) {
        return filterByValue(coupons, values, T::getMonth);
    }

    public static <T extends AbstractUpdateEntity> List<T> filterByYear(List<T> coupons, List<Integer> values) {
        return filterByNonEnum(coupons, values, T::getYear);
    }

    protected static <T, R> List<R> filterByNonEnum(List<R> input, List<T> values, Function<R, T> function) {
        return abstractFilter(input, values, function);
    }

    protected static <T extends IEnum, R> List<R> filterByValue(List<R> input, List<T> values, Function<R, T> function) {
        return abstractFilter(input, values, function);
    }

    private static <T, R> List<R> abstractFilter(List<R> input, List<T> values, Function<R, T> filterFunction) {
        return input.stream()
                .filter(coupon -> values.contains(filterFunction.apply(coupon)))
                .collect(Collectors.toList());
    }

}
