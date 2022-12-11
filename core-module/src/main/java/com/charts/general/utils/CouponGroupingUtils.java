package com.charts.general.utils;

import com.charts.general.entity.coupon.v2.CouponEntityV2;
import com.charts.general.entity.coupon.v2.CouponListV2;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CouponGroupingUtils extends AbstractGroupingUtils{

    public static Map<PersonType, List<CouponEntityV2>> groupByPersonType(List<?> couponEntityList) {
        return new CouponListV2(couponEntityList).getCouponEntityList().stream()
                .collect(Collectors.groupingBy(CouponEntityV2::getPersonType));
    }

    public static Map<String, Object> groupByAndSumByPerson(List<CouponEntityV2> entityList) {
        Map<String, Object> outputMap = new HashMap<>();
        entityList.stream()
                .collect(Collectors.groupingBy(CouponEntityV2::getPersonType, Collectors.summingInt(CouponEntityV2::getValue)))
                .forEach((personType, total) -> outputMap.put(personType.getValue(), total));
        return outputMap;
    }

    public static Map<Validity, List<CouponEntityV2>> groupByValidity(List<?> couponEntityList) {
        return sortByOrderValue(new CouponListV2(couponEntityList).getCouponEntityList().stream()
                .collect(Collectors.groupingBy(CouponEntityV2::getValidity)));
    }

    public static Map<Validity, Object> groupByAndSumByValidity(List<CouponEntityV2> entityList) {
        return sortByOrderValue(new HashMap<>(entityList.stream()
                .collect(Collectors.groupingBy(CouponEntityV2::getValidity, Collectors.summingInt(CouponEntityV2::getValue)))));
    }

    public static Map<SellType, List<CouponEntityV2>> groupBySellType(List<?> couponEntityList) {
        return sortByOrderValue(new CouponListV2(couponEntityList).getCouponEntityList().stream()
                .collect(Collectors.groupingBy(CouponEntityV2::getSellType)));
    }

    public static Map<SellType, Object> groupByAndSumBySellType(List<CouponEntityV2> entityList) {
        return sortByOrderValue(new HashMap<>(entityList.stream()
                .collect(Collectors.groupingBy(CouponEntityV2::getSellType, Collectors.summingInt(CouponEntityV2::getValue)))));
    }

}
