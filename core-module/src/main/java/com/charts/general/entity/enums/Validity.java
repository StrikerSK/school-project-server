package com.charts.general.entity.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

import static com.charts.general.constants.ValidityConstants.*;

@Getter
public enum Validity implements IEnum {

    MONTHLY(MONTHLY_LABEL, 1),
    THREE_MONTHS(THREE_MONTHS_LABEL, 2),
    FIVE_MONTHS(FIVE_MONTHS_LABEL, 3),
    YEARLY(YEARLY_LABEL, 4);

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
