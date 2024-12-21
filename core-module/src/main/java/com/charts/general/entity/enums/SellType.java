package com.charts.general.entity.enums;

import lombok.Getter;

import static com.charts.general.constants.EnumerationCouponConstants.CHIP_CARD;
import static com.charts.general.constants.EnumerationCouponConstants.E_SHOP;
import static com.charts.general.constants.EnumerationCouponConstants.PAPER_COUPON;

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

}
