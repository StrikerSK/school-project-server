package com.charts.general.entity.enums;

import java.util.Arrays;
import java.util.Optional;

import static com.charts.general.constants.EnumerationCouponConstants.*;

public enum PersonType implements IEnum {

    ADULT(ADULT_VALUE, 4),
    SENIOR(SENIOR_VALUE, 5),
    JUNIOR(JUNIOR_VALUE, 2),
    PORTABLE(PORTABLE_VALUE, 6),
    STUDENT(STUDENT_VALUE, 3),
    CHILDREN(CHILDREN_VALUE, 1);

    private final String value;
    private final Integer orderValue;

    PersonType(String value, Integer orderValue) {
        this.value = value;
        this.orderValue = orderValue;
    }

    public String getValue() {
        return value;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public static Optional<PersonType> getPersonType(String label) {
        return Arrays.stream(PersonType.values())
                .filter(c -> c.getValue().equals(label))
                .findFirst();
    }

}
