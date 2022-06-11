package com.charts.general.entity.enums;

import java.util.Optional;
import java.util.stream.Stream;

public enum SellType implements IEnum {

    CARD("Čipová karta"),
    COUPON("Papierový kupón"),
    ESHOP("EShop");

    private String value;

    private SellType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<SellType> sellTypeValue(String label) {
        return Stream.of(SellType.values())
                .filter(c -> c.getValue().equals(label))
                .findFirst();
    }

}
