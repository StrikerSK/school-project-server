package com.charts.general.entity.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnumUtils {

    public static <T extends Enum<T> & IEnum> List<T> getEnumValues(Class<T> enumClass) {
        if (enumClass == null || !enumClass.isEnum()) {
            throw new IllegalArgumentException("Provided class is not an enum.");
        }
        return Arrays.asList(enumClass.getEnumConstants());
    }

    public static <T extends Enum<T> & IEnum> Optional<T> findValue(Class<T> clazz, String label) {
        return getEnumValues(clazz).stream().filter(c -> c.getValue().equals(label)).findFirst();
    }

    public static <T extends Enum<T> & IEnum> List<T> findValue(Class<T> clazz, List<String> labels) {
        return getEnumValues(clazz).stream().filter(c -> labels.contains(c.getValue())).collect(Collectors.toList());
    }

    public static <T extends Enum<T> & IEnum> List<String> getLabels(Class<T> enumClass) {
        return getEnumValues(enumClass).stream().map(IEnum::getValue).collect(Collectors.toList());
    }
}
