package com.charts.general.entity.enums;

import lombok.Getter;

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

}
