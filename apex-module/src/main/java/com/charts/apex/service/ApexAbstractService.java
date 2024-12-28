package com.charts.apex.service;

import com.charts.apex.entity.ApexObject;
import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.IEnum;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

abstract class ApexAbstractService {

    protected static <T extends IEnum, R extends AbstractUpdateEntity> List<ApexObject> processValues(
            List<R> entryList,
            Function<List<R>, Map<T, List<R>>> upperFunction,
            Function<List<R>, Map<T, List<R>>> lowerFunction
    ) {
        return upperFunction.apply(entryList)
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getKey().getOrderValue()))
                .map(upper -> {
                    List<Integer> sum = lowerFunction.apply(upper.getValue()).entrySet()
                            .stream()
                            .sorted(Comparator.comparing(e -> e.getKey().getOrderValue()))
                            .map(Map.Entry::getValue)
                            .map(list -> list.stream()
                                    .map(R::getValue)
                                    .reduce(0,  Integer::sum))
                            .collect(Collectors.toList());
                    return new ApexObject(upper.getKey(), sum);
                })
                .collect(Collectors.toList());
    }

}
