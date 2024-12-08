package com.charts.general.utils;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.entity.v2.UpdateCouponList;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CouponGroupingUtils extends AbstractGroupingUtils{

    public static Map<PersonType, List<UpdateCouponEntity>> groupByPersonType(List<?> couponEntityList) {
        return new UpdateCouponList(couponEntityList).getCouponEntityList().stream()
                .collect(Collectors.groupingBy(UpdateCouponEntity::getPersonType));
    }

    public static Map<String, Object> groupByAndSumByPerson(List<UpdateCouponEntity> entityList) {
        Map<String, Object> outputMap = new HashMap<>();
        entityList.stream()
                .collect(Collectors.groupingBy(UpdateCouponEntity::getPersonType, Collectors.summingInt(UpdateCouponEntity::getValue)))
                .forEach((personType, total) -> outputMap.put(personType.getValue(), total));
        return outputMap;
    }

    public static Map<Validity, List<UpdateCouponEntity>> groupByValidity(List<?> couponEntityList) {
        return sortByOrderValue(new UpdateCouponList(couponEntityList).getCouponEntityList().stream()
                .collect(Collectors.groupingBy(UpdateCouponEntity::getValidity)));
    }

    public static Map<Validity, Object> groupByAndSumByValidity(List<UpdateCouponEntity> entityList) {
        return sortByOrderValue(new HashMap<>(entityList.stream()
                .collect(Collectors.groupingBy(UpdateCouponEntity::getValidity, Collectors.summingInt(UpdateCouponEntity::getValue)))));
    }

    public static Map<SellType, List<UpdateCouponEntity>> groupBySellType(List<?> couponEntityList) {
        return sortByOrderValue(new UpdateCouponList(couponEntityList).getCouponEntityList().stream()
                .collect(Collectors.groupingBy(UpdateCouponEntity::getSellType)));
    }

    public static Map<SellType, Object> groupByAndSumBySellType(List<UpdateCouponEntity> entityList) {
        return sortByOrderValue(new HashMap<>(entityList.stream()
                .collect(Collectors.groupingBy(UpdateCouponEntity::getSellType, Collectors.summingInt(UpdateCouponEntity::getValue)))));
    }

}
