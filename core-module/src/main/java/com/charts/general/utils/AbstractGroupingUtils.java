package com.charts.general.utils;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.Months;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
