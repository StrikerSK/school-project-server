package com.charts.general.entity;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.charts.general.utils.ParameterUtils.verifyMonthsList;

public abstract class AbstractParameters {

    private final List<String> month;
    private final List<Integer> year;

    protected AbstractParameters(List<String> month, List<Integer> year) {
        this.month = month;
        this.year = year;
    }

    public List<String> getMonth() {
        return verifyMonthsList(month);
    }

    public List<Integer> getYearInteger() {
        if (CollectionUtils.isEmpty(year)) {
            return Stream.of(2015, 2016, 2017, 2018, 2019, 2020).collect(Collectors.toList());
        }

        return year;
    }
}
