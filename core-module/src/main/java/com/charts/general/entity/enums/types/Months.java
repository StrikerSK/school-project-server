package com.charts.general.entity.enums.types;

import com.charts.general.entity.enums.IEnum;
import lombok.Getter;

import static com.charts.api.coupon.constants.EnumerationCouponConstants.APRIL_VALUE;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.AUGUST_VALUE;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.DECEMBER_VALUE;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.FEBRUARY_VALUE;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.JANUARY_VALUE;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.JULY_VALUE;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.JUNE_VALUE;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.MARCH_VALUE;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.MAY_VALUE;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.NOVEMBER_VALUE;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.OCTOBER_VALUE;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.SEPTEMBER_VALUE;

@Getter
public enum Months implements IEnum {

    JANUARY(JANUARY_VALUE, 1),
    FEBRUARY(FEBRUARY_VALUE, 2),
    MARCH(MARCH_VALUE, 3),
    APRIL(APRIL_VALUE, 4),
    MAY(MAY_VALUE, 5),
    JUNE(JUNE_VALUE, 6),
    JULY(JULY_VALUE, 7),
    AUGUST(AUGUST_VALUE, 8),
    SEPTEMBER(SEPTEMBER_VALUE, 9),
    OCTOBER(OCTOBER_VALUE, 10),
    NOVEMBER(NOVEMBER_VALUE, 11),
    DECEMBER(DECEMBER_VALUE, 12);

    private final String value;
    private final Integer orderValue;

    Months(String value, Integer orderValue) {
        this.value = value;
        this.orderValue = orderValue;
    }

}
