package com.charts.general.entity.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.charts.general.entity.enums.EnumTypes.*;

public class EnumUtils {

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

}
