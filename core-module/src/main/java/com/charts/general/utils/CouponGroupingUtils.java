package com.charts.general.utils;

import com.charts.general.entity.coupon.v2.CouponEntityV2;
import com.charts.general.entity.coupon.v2.UpdateCouponList;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CouponGroupingUtils extends AbstractGroupingUtils{

    public static Map<PersonType, List<CouponEntityV2>> groupByPersonType(List<CouponEntityV2> couponEntityList) {
        return couponEntityList.stream().collect(Collectors.groupingBy(CouponEntityV2::getPersonType));
    }

    public static Map<String, Integer> groupAndSumByPerson(List<CouponEntityV2> entities) {
        Map<String, Integer> outputMap = new HashMap<>();
        entities.stream()
                .collect(Collectors.groupingBy(CouponEntityV2::getPersonType, Collectors.summingInt(CouponEntityV2::getValue)))
                .forEach((personType, total) -> outputMap.put(personType.getValue(), total));
        return outputMap;
    }

    public static Map<Validity, List<CouponEntityV2>> groupByValidity(List<CouponEntityV2> couponEntityList) {
        return new UpdateCouponList(couponEntityList)
                .getCouponEntityList()
                .stream()
                .collect(Collectors.groupingBy(CouponEntityV2::getValidity));
    }

    public static Map<Validity, Integer> groupByAndSumByValidity(List<CouponEntityV2> entityList) {
        return sortByOrderValue(
                new HashMap<>(
                        entityList.stream()
                                .collect(Collectors.groupingBy(CouponEntityV2::getValidity, Collectors.summingInt(CouponEntityV2::getValue)))
                )
        );
    }

    /**
     * Groups the given list of entities by SellType and returns a map of SellType to a list of coupon entities.
     *
     * @param  entities  The list of entities to be grouped
     * @return          A map of SellType to a list of coupon entities
     */
    public static Map<SellType, List<CouponEntityV2>> groupBySellType(List<?> entities) {
        return sortByOrderValue(
                new UpdateCouponList(entities).getCouponEntityList().stream()
                        .collect(Collectors.groupingBy(CouponEntityV2::getSellType))
        );
    }


    /**
     * Groups the list of CouponEntityV2 objects by SellType and sums the values for each SellType.
     *
     * @param  entities  The list of CouponEntityV2 objects to be grouped and summed
     * @return           A map with SellType as key and the sum of values for each SellType as value
     */
    public static Map<SellType, Integer> groupByAndSumBySellType(List<CouponEntityV2> entities) {
        return sortByOrderValue(
                entities.stream()
                        .collect(Collectors.groupingBy(CouponEntityV2::getSellType, Collectors.summingInt(CouponEntityV2::getValue))));
    }

}
