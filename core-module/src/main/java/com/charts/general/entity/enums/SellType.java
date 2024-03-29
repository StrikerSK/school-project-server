package com.charts.general.entity.enums;

import lombok.Getter;

@Getter
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

}
