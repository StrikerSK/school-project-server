package com.charts.general.utils;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.Months;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractGroupingUtils {

    public static <T extends AbstractUpdateEntity> Map<Months, Object> groupAndSumByMonth(List<T> entityList) {
        return sortByOrderValue(new HashMap<>(entityList.stream().collect(Collectors.groupingBy(T::getMonth, Collectors.summingInt(T::getValue)))));
    }

    public static <T extends IEnum> Map<String, Object> convertMapKeysToString(Map<T, Object> map) {
        return map.entrySet().stream().collect(Collectors.toMap(k -> k.getKey().getValue(), Map.Entry::getValue));
    }

    /**
     *
     * @param entityList
     * @return
     * @param <T>
     */
    protected static <T extends IEnum, R extends AbstractUpdateEntity> Map<T, Object> aggregateGroupsSum(Map<T, List<R>> entityList) {
        return entityList.entrySet()
                .stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), aggregateGroupSum(e.getValue()).longValue()))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    protected static <T extends AbstractUpdateEntity> Integer aggregateGroupSum(List<T> entityList) {
        return entityList.stream().mapToInt(T::getValue).sum();
    }

    /**
     * Method creates groups of values based on provided grouping function
     * @param entryList Simple list of value that are supposed to be grouped
     * @param groupingFunction Function that would be used to group values
     *
     * @return Map of values that are supposed to be groups per enumeration implementing {@link IEnum} IEnum interface
     * @param <T> Enumeration implementing {@link IEnum}
     * @param <R> Type of value that will be implementing {@link AbstractUpdateEntity}
     */
    protected static <T extends IEnum, R> Map<T, List<R>> groupValues(List<R> entryList, Function<R, T> groupingFunction) {
        return entryList
                .stream()
                .collect(Collectors.groupingBy(groupingFunction));
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
