package com.charts.general.entity.enums;

import java.util.Optional;
import java.util.stream.Stream;

public enum SellType implements IEnum {

    CARD("Čipová karta", 1),
    COUPON("Papierový kupón", 2),
    ESHOP("EShop", 3);

    private final String value;
    private final Integer orderValue;

    SellType(String value, Integer orderValue) {
        this.value = value;
        this.orderValue = orderValue;
    }

    public String getValue() {
        return value;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public static Optional<SellType> sellTypeValue(String label) {
        return Stream.of(SellType.values())
                .filter(c -> c.getValue().equals(label))
                .findFirst();
    }

}
