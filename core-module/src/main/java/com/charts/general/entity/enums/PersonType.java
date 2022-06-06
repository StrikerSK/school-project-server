package com.charts.general.entity.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.charts.general.constants.PersonType.*;

public enum PersonType {

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

    public static List<String> getStringValues() {
        return Arrays.stream(values())
                .map(PersonType::getValue)
                .collect(Collectors.toList());
    }

    public static PersonType getEnumType(String label) {
        if (label == null) {
            return null;
        }

        return Stream.of(PersonType.values())
                .filter(c -> c.getValue().equals(label))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<PersonType> getEnumList() {
        return Arrays.asList(PersonType.values());
    }
}
