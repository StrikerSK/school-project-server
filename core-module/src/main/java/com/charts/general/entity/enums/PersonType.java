package com.charts.general.entity.enums;

import java.util.Arrays;
import java.util.Optional;

import static com.charts.general.constants.PersonType.*;

public enum PersonType implements IEnum {

    ADULT(ADULT_VALUE),
    SENIOR(SENIOR_VALUE),
    JUNIOR(JUNIOR_VALUE),
    PORTABLE(PORTABLE_VALUE),
    STUDENT(STUDENT_VALUE),
    CHILDREN(CHILDREN_VALUE);

    private final String value;

    PersonType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<PersonType> getPersonType(String label) {
        return Arrays.stream(PersonType.values())
                .filter(c -> c.getValue().equals(label))
                .findFirst();
    }

}
