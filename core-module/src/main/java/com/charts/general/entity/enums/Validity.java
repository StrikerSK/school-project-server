package com.charts.general.entity.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Validity implements IEnum {

    MONTHLY("Mesačná", 1),
    THREE_MONTHS("3 Mesačná", 2),
    FIVE_MONTHS("5 Mesačná", 3),
    YEARLY("Ročná", 4);

    public static final List<Validity> VALIDITY_LIST = EnumUtils.getValueList(Validity.class);

    @Getter
    private final String value;
    private final Integer orderValue;

    Validity(String value, Integer orderValue) {
        this.value = value;
        this.orderValue = orderValue;
    }

    @Override
    public Integer getOrderValue() {
        return orderValue;
    }

    public static Optional<Validity> validityValue(String label) {
        return Arrays.stream(Validity.values())
                .filter(c -> c.getValue().equals(label))
                .findFirst();
    }

}
