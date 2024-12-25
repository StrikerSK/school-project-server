package com.charts.general.utils;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.types.EnumProxy;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.types.Months;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractGroupingUtils {

    public static <T extends AbstractUpdateEntity> Map<EnumProxy, List<T>> groupByYear(List<T> couponEntityList) {
        return couponEntityList.stream().collect(Collectors.groupingBy(t -> new EnumProxy(t.getYear())));
    }

    public static <T extends AbstractUpdateEntity> Map<Months, List<T>> groupByMonth(List<T> entityList) {
        return groupValues(entityList, T::getMonth);
    }

    public static <T extends IEnum> Map<String, Object> convertMapKeysToString(Map<T, Object> map) {
        return map.entrySet().stream().collect(Collectors.toMap(k -> k.getKey().getValue(), Map.Entry::getValue));
    }

    /**
     * Method aggregates all groups by sum of values
     * @param entityList Map of values that are supposed to be groups per enumeration implementing {@link IEnum}
     * @return Map of values that are supposed to be groups per enumeration implementing {@link IEnum}
     *
     * @param <T> Enumeration implementing {@link IEnum}
     * @param <R> Values that are implementing implementing {@link AbstractUpdateEntity}
     */
    public static <T extends IEnum, R extends AbstractUpdateEntity> Map<T, Object> aggregateGroupsSum(Map<T, List<R>> entityList) {
        return entityList.entrySet()
                .stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), aggregateGroupSum(e.getValue()).longValue()))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    /**
     * Method sums all values in list
     * @param entityList List of values
     * @return Sum of values
     * @param <T> Type of value that will be implementing {@link AbstractUpdateEntity}
     */
    public static <T extends AbstractUpdateEntity> Integer aggregateGroupSum(List<T> entityList) {
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

}
