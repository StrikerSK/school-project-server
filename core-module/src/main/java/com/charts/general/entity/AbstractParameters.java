package com.charts.general.entity;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.Validity;
import org.apache.commons.collections4.CollectionUtils;

import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractParameters {

    private final List<String> month;
    private final List<Integer> year;

    protected AbstractParameters(List<String> month, List<Integer> year) {
        this.month = month;
        this.year = year;
    }

    public List<Months> getMonths() {
        if (CollectionUtils.isEmpty(month)) {
            return EnumUtils.getValueList(Months.class);
        } else {
            return month.stream()
                    .map(Months::getType)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    public List<Integer> getYearInteger() {
        if (CollectionUtils.isEmpty(year)) {
            return Stream.of(2015, 2016, 2017, 2018, 2019, 2020).collect(Collectors.toList());
        }

        return year;
    }
}
