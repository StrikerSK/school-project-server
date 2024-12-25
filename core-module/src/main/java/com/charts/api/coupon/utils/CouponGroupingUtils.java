package com.charts.api.coupon.utils;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import com.charts.general.utils.AbstractGroupingUtils;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CouponGroupingUtils extends AbstractGroupingUtils {

    public static Map<PersonType, List<UpdateCouponEntity>> groupByPersonType(List<UpdateCouponEntity> couponEntityList) {
        return groupValues(couponEntityList, UpdateCouponEntity::getPersonType);
    }

    public static Map<Validity, List<UpdateCouponEntity>> groupByValidity(List<UpdateCouponEntity> couponEntityList) {
        return groupValues(couponEntityList, UpdateCouponEntity::getValidity);
    }

    public static Map<SellType, List<UpdateCouponEntity>> groupBySellType(List<UpdateCouponEntity> couponEntityList) {
        return groupValues(couponEntityList, UpdateCouponEntity::getSellType);
    }

    public static <T extends IEnum> Map<T, Long> sumGroup(Map<T, List<UpdateCouponEntity>> entityList) {
        return entityList.entrySet()
                .stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), sumGroup(e.getValue()).longValue()))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

    }

    public static Integer sumGroup(List<UpdateCouponEntity> entityList) {
        return entityList.stream().mapToInt(UpdateCouponEntity::getValue).sum();
    }

}
