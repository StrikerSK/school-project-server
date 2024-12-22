package com.charts.api.coupon.utils;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.general.entity.enums.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CouponFilterUtils {

    public static List<UpdateCouponEntity> filterByMonth(List<UpdateCouponEntity> coupons, List<Months> values) {
        return filterByValue(coupons, values, UpdateCouponEntity::getMonth);
    }

    public static List<UpdateCouponEntity> filterBySellType(List<UpdateCouponEntity> coupons, List<SellType> values) {
        return filterByValue(coupons, values, UpdateCouponEntity::getSellType);
    }

    public static List<UpdateCouponEntity> filterByValidity(List<UpdateCouponEntity> coupons, List<Validity> values) {
        return filterByValue(coupons, values, UpdateCouponEntity::getValidity);
    }

    public static List<UpdateCouponEntity> filterByPersonType(List<UpdateCouponEntity> coupons, List<PersonType> values) {
        return filterByValue(coupons, values, UpdateCouponEntity::getPersonType);
    }

    private static <T extends IEnum> List<UpdateCouponEntity> filterByValue(List<UpdateCouponEntity> coupons, List<T> values, Function<UpdateCouponEntity, T> function) {
        return coupons.stream()
                .filter(coupon -> values.contains(function.apply(coupon)))
                .collect(Collectors.toList());
    }

}
