package com.charts.nivo.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.api.coupon.utils.CouponGroupingUtils;
import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.general.entity.enums.IEnum;
import com.charts.nivo.Utils.NivoConvertersUtils;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.Comparator;

@Service
public class NivoCouponService {

    private final CouponV2Service couponService;

    public NivoCouponService(CouponV2Service couponService) {
        this.couponService = couponService;
    }

    public List<NivoPieData> createDynamicPieData(String groupName, CouponsParameters parameters) {
        List<NivoPieData> convertedData;

        switch (groupName) {
            case "person":
                convertedData = NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedByPersonType(parameters));
                break;
            case "month":
                convertedData = NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedByMonth(parameters));
                break;
            case "sell":
                convertedData = NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedBySellType(parameters));
                break;
            case "validity":
                convertedData = NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedByValidity(parameters));
                break;
            case "year":
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
            throw new IllegalArgumentException("Cannot use same groups!");
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends IEnum> Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> createGrouping(String groupName) {
        Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> convertedData;

        switch (groupName) {
            case "person":
                convertedData = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByPersonType(e);
                break;
            case "month":
                convertedData = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByMonth(e);
                break;
            case "sell":
                convertedData = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupBySellType(e);
                break;
            case "validity":
                convertedData = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByValidity(e);
                break;
            case "year":
                convertedData = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByYear(e);
                break;
            default:
                throw new IllegalArgumentException("Unknown group name: " + groupName);
        }

        return convertedData;
    }

}
