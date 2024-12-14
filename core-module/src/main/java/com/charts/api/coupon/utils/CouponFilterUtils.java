package com.charts.api.coupon.utils;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.SellType;

import java.util.List;
import java.util.stream.Collectors;

public class CouponFilterUtils {

    public static List<UpdateCouponEntity> filterByMonth(List<UpdateCouponEntity> coupons, List<Months> months) {
        return coupons.stream()
                .filter(coupon -> months.contains(coupon.getMonth()))
                .collect(Collectors.toList());
    }

    public static List<UpdateCouponEntity> filterBySellType(List<UpdateCouponEntity> coupons, List<SellType> months) {
        return coupons.stream()
                .filter(coupon -> months.contains(coupon.getSellType()))
                .collect(Collectors.toList());
    }

}
