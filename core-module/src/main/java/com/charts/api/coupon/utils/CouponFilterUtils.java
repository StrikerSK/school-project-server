package com.charts.api.coupon.utils;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.general.entity.enums.*;
import com.charts.general.utils.AbstractFilterUtils;

import java.util.List;

public class CouponFilterUtils extends AbstractFilterUtils {

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

}
