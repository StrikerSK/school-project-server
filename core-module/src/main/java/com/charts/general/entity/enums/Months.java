package com.charts.general.entity.enums;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.charts.general.constants.Months.*;

public enum Months implements IEnum {

    JANUARY(JANUARY_VALUE, 1),
    FEBRUARY(FEBRUARY_VALUE, 2),
    MARCH(MARCH_VALUE, 3),
    APRIL(APRIL_VALUE, 4),
    MAY(MAY_VALUE, 5),
    JUNE(JUNE_VALUE, 6),
    JULY(JULY_VALUE, 7),
    AUGUST(AUGUST_VALUE, 8),
    SEPTEMBER(SEPTEMBER_VALUE, 9),
    OCTOBER(OCTOBER_VALUE, 10),
    NOVEMBER(NOVEMBER_VALUE, 11),
    DECEMBER(DECEMBER_VALUE, 12);

    private final String value;
    private final Integer orderValue;

    Months(String value, Integer orderValue) {
        this.value = value;
        this.orderValue = orderValue;
    }

    public String getValue() {
        return value;
    }

    public static Optional<Months> getType(String label) {
        return Arrays.stream(Months.values())
                .filter(c -> c.getValue().equals(label))
                .findFirst();
    }

    public Integer getOrderValue() {return orderValue; }

    public static List<String> monthsValues() {
        return Arrays.stream(values())
                .map(Months::getValue)
                .collect(Collectors.toList());
    }

}
