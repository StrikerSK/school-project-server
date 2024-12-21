package com.charts.general.entity.enums;

import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

import static com.charts.general.constants.EnumerationCouponConstants.*;

@Getter
public enum SellType implements IEnum {

    CARD(CHIP_CARD, 1),
    COUPON(PAPER_COUPON, 2),
    ESHOP(E_SHOP, 3);

    private final String value;
    private final Integer orderValue;

    SellType(String value, Integer orderValue) {
        this.value = value;
        this.orderValue = orderValue;
    }

    public static Optional<SellType> sellTypeValue(String label) {
        return Stream.of(SellType.values())
                .filter(c -> c.getValue().equals(label))
                .findFirst();
    }

}
