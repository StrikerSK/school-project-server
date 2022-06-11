package com.charts.general.utils;

import com.charts.general.entity.coupon.updated.UpdateCouponEntity;
import com.charts.general.entity.coupon.updated.UpdateCouponList;
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
        return new UpdateCouponList(couponEntityList).getCouponEntityList().stream()
                .collect(Collectors.groupingBy(UpdateCouponEntity::getValidity));
    }

    public static Map<String, Object> groupByAndSumByValidity(List<UpdateCouponEntity> entityList) {
        Map<String, Object> outputMap = new HashMap<>();
        entityList.stream()
                .collect(Collectors.groupingBy(UpdateCouponEntity::getValidity, Collectors.summingInt(UpdateCouponEntity::getValue)))
                .forEach((validity, total) -> outputMap.put(validity.getValue(), total));
        return outputMap;
    }

    public static Map<SellType, List<UpdateCouponEntity>> groupBySellType(List<?> couponEntityList) {
        return new UpdateCouponList(couponEntityList).getCouponEntityList().stream()
                .collect(Collectors.groupingBy(UpdateCouponEntity::getSellType));
    }

    public static Map<String, Object> groupByAndSumBySellType(List<UpdateCouponEntity> entityList) {
        Map<String, Object> outputMap = new HashMap<>();
        entityList.stream()
                .collect(Collectors.groupingBy(UpdateCouponEntity::getSellType, Collectors.summingInt(UpdateCouponEntity::getValue)))
                .forEach((sellType, total) -> outputMap.put(sellType.getValue(), total));
        return outputMap;
    }

}
