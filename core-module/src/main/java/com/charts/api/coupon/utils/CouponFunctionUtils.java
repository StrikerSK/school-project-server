package com.charts.api.coupon.utils;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.exception.InvalidParameterException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CouponFunctionUtils {

    public static final String MONTH_GROUP = "month";
    public static final String YEAR_GROUP = "year";
    public static final String PERSON_GROUP = "person";
    public static final String SELL_GROUP = "sell";
    public static final String VALIDITY_GROUP = "validity";

    public static void validateGroups(String upperGroup, String lowerGroup) {
        if (upperGroup.equals(lowerGroup)) {
            throw new InvalidParameterException("Cannot use same groups");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends IEnum> Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> createGrouping(String groupName) {
        Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> convertedData;

        switch (groupName.toLowerCase()) {
            case PERSON_GROUP:
                convertedData = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByPersonType(e);
                break;
            case MONTH_GROUP:
                convertedData = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByMonth(e);
                break;
            case SELL_GROUP:
                convertedData = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupBySellType(e);
                break;
            case VALIDITY_GROUP:
                convertedData = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByValidity(e);
                break;
            case YEAR_GROUP:
                convertedData = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByYear(e);
                break;
            default:
                throw new InvalidParameterException(
                        String.format("Unknown group name: %s! Available groups: %s, %s, %s, %s, %s", groupName, YEAR_GROUP, MONTH_GROUP, SELL_GROUP, VALIDITY_GROUP, PERSON_GROUP)
                );
        }

        return convertedData;
    }

}
