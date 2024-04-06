package com.charts.general.utils;

import com.charts.general.entity.AbstractEntityV2;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.Months;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractGroupingUtils {

    public static <T extends AbstractEntityV2> Map<String, List<T>> groupByCode(List<T> entities) {
        return entities.stream().collect(Collectors.groupingBy(AbstractEntityV2::getCode));
    }

    public static <T extends AbstractEntityV2> Map<String, Object> groupAndSumByCode(List<T> entityList) {
        return new HashMap<>(entityList.stream().collect(Collectors.groupingBy(AbstractEntityV2::getCode, Collectors.summingInt(AbstractEntityV2::getValue))));
    }

    public static <T extends AbstractEntityV2> Map<Months, List<T>> groupByMonth(List<T> entityList) {
        return entityList.stream().collect(Collectors.groupingBy(AbstractEntityV2::getMonth));
    }

    public static <T extends AbstractEntityV2> Map<Months, Integer> groupAndSumByMonth(List<T> entities) {
        return sortByOrderValue(entities.stream()
                .collect(Collectors.groupingBy(AbstractEntityV2::getMonth, Collectors.summingInt(AbstractEntityV2::getValue))));
    }

    public static <T extends AbstractEntityV2> Map<Integer, List<AbstractEntityV2>> groupByYear(List<T> entityList) {
        return ListFactory.getList(entityList).stream().map(e -> (AbstractEntityV2) e).collect(Collectors.groupingBy(AbstractEntityV2::getYear));
    }

    public static <T extends AbstractEntityV2> Map<Integer, Integer> groupAndSumByYear(List<T> entities) {
        return entities.stream().collect(Collectors.groupingBy(AbstractEntityV2::getYear, Collectors.summingInt(AbstractEntityV2::getValue)));
    }

    public static <T extends IEnum> Map<String, Object> convertMapKeysToString(Map<T, Object> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getValue(), Map.Entry::getValue));
    }

    protected static <R extends IEnum, T> Map<R, T> sortByOrderValue(Map<R, T> customMap) {
        Map<R, T> sortedMap = new TreeMap<>(Comparator.comparingInt(IEnum::getOrderValue));
        sortedMap.putAll(customMap);
        return sortedMap;
    }

}
