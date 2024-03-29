package com.charts.general.entity.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

import static com.charts.general.constants.PersonType.*;

@Getter
public enum PersonType implements IEnum {

    CHILDREN(CHILDREN_VALUE, 1),
    JUNIOR(JUNIOR_VALUE, 2),
    STUDENT(STUDENT_VALUE, 3),
    ADULT(ADULT_VALUE, 4),
    SENIOR(SENIOR_VALUE, 5),
    PORTABLE(PORTABLE_VALUE, 6);

    private final String value;
    private final Integer orderValue;

    PersonType(String value, Integer orderValue) {
        this.value = value;
        this.orderValue = orderValue;
    }

    public static Optional<PersonType> getPersonType(String label) {
        return Arrays.stream(PersonType.values())
                .filter(c -> c.getValue().equals(label))
                .findFirst();
    }

}
