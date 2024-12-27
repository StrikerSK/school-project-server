package com.charts.nivo.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.api.coupon.utils.CouponGroupingUtils;
import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.exception.InvalidParameterException;
import com.charts.nivo.Utils.NivoConvertersUtils;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.Comparator;

@Service
@AllArgsConstructor
public class NivoCouponService {

    private final CouponV2Service couponService;

    private final String MONTH_GROUP = "month";
    private final String YEAR_GROUP = "year";
    private final String PERSON_GROUP = "person";
    private final String SELL_GROUP = "sell";
    private final String VALIDITY_GROUP = "validity";

    public List<NivoPieData> createDynamicPieData(String groupName, CouponsParameters parameters) {
        List<NivoPieData> convertedData;

        switch (groupName.toLowerCase()) {
            case PERSON_GROUP:
                convertedData = NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedByPersonType(parameters));
                break;
            case MONTH_GROUP:
                convertedData = NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedByMonth(parameters));
                break;
            case SELL_GROUP:
                convertedData = NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedBySellType(parameters));
                break;
            case VALIDITY_GROUP:
                convertedData = NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedByValidity(parameters));
                break;
            case YEAR_GROUP:
                convertedData = NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedByYear(parameters));
                break;
            default:
                throw new IllegalArgumentException("Unknown group name: " + groupName);
        }

        convertedData.sort(Comparator.comparingInt(NivoPieData::getOrderValue));
        return convertedData;
    }

    public <T extends IEnum> List<NivoLineData> createDynamicLineData(String upperGroup, String lowerGroup, CouponsParameters parameters) {
        validateGroups(upperGroup, lowerGroup);
        Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> upperGroupingFunction = createGrouping(upperGroup);
        Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> lowerGroupingFunction = createGrouping(lowerGroup);
        return NivoConvertersUtils.createLineData(
                couponService.findCouponEntities(parameters),
                upperGroupingFunction,
                lowerGroupingFunction,
                CouponGroupingUtils::aggregateGroupSum
        );
    }

    public <T extends IEnum> NivoBubbleData createDynamicBubbleData(String upperGroup, String lowerGroup, CouponsParameters parameters) {
        validateGroups(upperGroup, lowerGroup);
        Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> upperGroupingFunction = createGrouping(upperGroup);
        Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> lowerGroupingFunction = createGrouping(lowerGroup);
        return NivoConvertersUtils.createBubbleData(
                couponService.findCouponEntities(parameters),
                upperGroupingFunction,
                lowerGroupingFunction,
                CouponGroupingUtils::aggregateGroupSum
        );
    }

    private void validateGroups(String upperGroup, String lowerGroup) {
        if (upperGroup.equals(lowerGroup)) {
            throw new InvalidParameterException("Cannot use same groups");
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends IEnum> Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> createGrouping(String groupName) {
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
