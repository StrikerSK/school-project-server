package com.charts.general.utils;

import com.charts.general.entity.AbstractEntityV2;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.Months;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public abstract class AbstractGroupingUtils {

    public static <T extends AbstractEntityV2> Map<String, List<T>> groupByCode(List<T> entityList) {
        return ListFactory.getList(entityList).stream()
                .map(e -> (T) e)
                .collect(Collectors.groupingBy(T::getCode));
    }

    public static <T extends AbstractEntityV2> Map<String, Object> groupAndSumByCode(List<T> entityList) {
        return new HashMap<>(entityList.stream().collect(Collectors.groupingBy(T::getCode, Collectors.summingInt(T::getValue))));
    }

    public static <T extends AbstractEntityV2> Map<Months, List<T>> groupByMonth(List<T> entityList) {
        return sortByOrderValue(ListFactory.getList(entityList).stream()
                .map(e -> (T) e)
                .collect(Collectors.groupingBy(T::getMonth)));
    }

    public static <T extends AbstractEntityV2> Map<Months, Object> groupAndSumByMonth(List<T> entityList) {
        return sortByOrderValue(new HashMap<>(entityList.stream().collect(Collectors.groupingBy(T::getMonth, Collectors.summingInt(T::getValue)))));
    }

    public static <T extends AbstractEntityV2> Map<Integer, List<T>> groupByYear(List<T> entityList) {
        return ListFactory.getList(entityList).stream().map(e -> (T) e).collect(Collectors.groupingBy(T::getYear));
    }

    public static <T extends AbstractEntityV2> Map<Integer, Object> groupAndSumByYear(List<T> entityList) {
        return new HashMap<>(entityList.stream().collect(Collectors.groupingBy(T::getYear, Collectors.summingInt(T::getValue))));
    }

    public static <T extends IEnum> Map<String, Object> convertMapKeysToString(Map<T, Object> map) {
        return map.entrySet().stream().collect(Collectors.toMap(k -> k.getKey().getValue(), Map.Entry::getValue));
    }

    protected static <R extends IEnum, T> Map<R, T> sortByOrderValue(Map<R, T> customMap) {
        Map<R, T> sortedMap = new TreeMap<>(
                (o1, o2) -> {
                    Integer thisOrderValue = o1.getOrderValue();
                    Integer nextOrderValue = o2.getOrderValue();

                    if (thisOrderValue.equals(nextOrderValue)) {
                        return 0;
                    } else if (thisOrderValue > nextOrderValue) {
                        return 1;
                    } else {
                        return -1;
                    }
                });

        sortedMap.putAll(customMap);
        return sortedMap;
    }

}
