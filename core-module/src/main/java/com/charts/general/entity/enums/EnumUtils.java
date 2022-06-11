package com.charts.general.entity.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.charts.general.entity.enums.EnumTypes.*;

public class EnumUtils {

    public static <T extends IEnum> List<T> getValueList(Class<T> clazz) {
        List<?> tmpValue = new ArrayList<>();

        if (clazz == null) {
            return null;
        } else if (clazz == Months.class) {
            tmpValue = Arrays.asList(Months.values());
        } else if (clazz == PersonType.class) {
            tmpValue = Arrays.asList(PersonType.values());
        } else if (clazz == SellType.class) {
            tmpValue = Arrays.asList(SellType.values());
        } else if (clazz == TicketTypes.class) {
            tmpValue = Arrays.asList(TicketTypes.values());
        } else if (clazz == Validity.class) {
            tmpValue = Arrays.asList(Validity.values());
        }

        return tmpValue.stream().map(e -> (T) e).collect(Collectors.toList());
    }

    public static List<IEnum> getValueList(EnumTypes enumType) {
        if (enumType == null) {
            return null;
        } else if (enumType == MONTH_ENUM) {
            return Arrays.asList(Months.values());
        } else if (enumType == PERSON_TYPE_ENUM) {
            return Arrays.asList(PersonType.values());
        } else if (enumType == SELL_TYPE_ENUM) {
            return Arrays.asList(SellType.values());
        } else if (enumType == TICKET_TYPE_ENUM) {
            return Arrays.asList(TicketTypes.values());
        } else if (enumType == VALIDITY_ENUM) {
            return Arrays.asList(Validity.values());
        }

        return null;
    }

    public static Optional<IEnum> getValue(EnumTypes enumType, String label) {
        return getValueList(enumType).stream()
                .filter(c -> c.getValue().equals(label))
                .findFirst();
    }

    public static <T extends IEnum> Optional<T> getValue(Class<T> clazz, String label) {
        return getValueList(clazz).stream()
                .filter(c -> c.getValue().equals(label))
                .findFirst();
    }

    public static List<String> getStringValues(EnumTypes enumType) {
        return getValueList(enumType).stream()
                .map(IEnum::getValue)
                .collect(Collectors.toList());
    }

    public static <T extends IEnum> List<String> getStringValues(Class<T> clazz) {
        return getValueList(clazz).stream()
                .map(IEnum::getValue)
                .collect(Collectors.toList());
    }

}
