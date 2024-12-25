package com.charts.nivo.service;

import com.charts.general.entity.GroupingEntity;
import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.api.coupon.utils.CouponGroupingUtils;
import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.general.entity.enums.IEnum;
import com.charts.nivo.Utils.NivoConvertersUtils;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NivoCouponService {

    private final CouponV2Service couponService;

    /**
     * Method gets line data by Person Type
     *
     * @param parameters all requested parameters
     * @return data for displaying line chart by Person Type
     */
    public List<NivoLineData> getMonthlyLineDataByPersonType(CouponsParameters parameters) {
        return NivoConvertersUtils.createLineData(
                couponService.findCouponEntities(parameters),
                CouponGroupingUtils::groupByPersonType,
                CouponGroupingUtils::groupByMonth,
                CouponGroupingUtils::aggregateGroupSum
        );
    }

    /**
     * Method gets line data by Validity
     *
     * @param parameters all requested parameters
     * @return data for displaying line chart by Validity
     */
    public List<NivoLineData> getMonthlyLineDataByValidity(CouponsParameters parameters) {
        return NivoConvertersUtils.createLineData(
                couponService.findCouponEntities(parameters),
                CouponGroupingUtils::groupByValidity,
                CouponGroupingUtils::groupByMonth,
                CouponGroupingUtils::aggregateGroupSum
        );
    }

    /**
     * Method gets line data by Sell Type
     *
     * @param parameters all requested parameters
     * @return data for displaying line chart by Sell Type
     */
    public List<NivoLineData> getMonthlyLineDataBySellType(CouponsParameters parameters) {
        return NivoConvertersUtils.createLineData(
                couponService.findCouponEntities(parameters),
                CouponGroupingUtils::groupBySellType,
                CouponGroupingUtils::groupByMonth,
                CouponGroupingUtils::aggregateGroupSum
        );
    }

    public List<Map<String, Object>> getMonthlyBarDataByPersonType(CouponsParameters parameters) {
        return NivoConvertersUtils.createBarData(
                couponService.findCouponEntities(parameters),
                CouponGroupingUtils::groupByMonth,
                CouponGroupingUtils::groupByPersonType,
                CouponGroupingUtils::aggregateGroupSum
        );
    }

    public List<Map<String, Object>> getMonthlyBarDataByValidity(CouponsParameters parameters) {
        return NivoConvertersUtils.createBarData(
                couponService.findCouponEntities(parameters),
                CouponGroupingUtils::groupByMonth,
                CouponGroupingUtils::groupByValidity,
                CouponGroupingUtils::aggregateGroupSum
        );
    }

    public List<Map<String, Object>> getMonthlyBarDataBySellType(CouponsParameters parameters) {
        return NivoConvertersUtils.createBarData(
                couponService.findCouponEntities(parameters),
                CouponGroupingUtils::groupByMonth,
                CouponGroupingUtils::groupBySellType,
                CouponGroupingUtils::aggregateGroupSum
        );
    }

    public NivoBubbleData getPersonBubbleDataByValidity(CouponsParameters parameters) {
        return createBubbleData(parameters, CouponGroupingUtils::groupByPersonType, CouponGroupingUtils::groupByValidity);
    }

    public NivoBubbleData getPersonBubbleDataBySellType(CouponsParameters parameters) {
        return createBubbleData(parameters, CouponGroupingUtils::groupByPersonType, CouponGroupingUtils::groupBySellType);
    }

    /**
     * Method gets data for displaying bubble chart. It is divided by two grouping upper grouping and nested grouping.
     *
     * @param parameters all parameters obtained from request
     * @param upperGrouping top level grouping of data
     * @param lowerGrouping nested grouping of withing upper grouping
     */
    public <T extends IEnum, R extends IEnum> NivoBubbleData createBubbleData(
            CouponsParameters parameters,
            Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> upperGrouping,
            Function<List<UpdateCouponEntity>, Map<R, List<UpdateCouponEntity>>> lowerGrouping
    ) {
        List<UpdateCouponEntity> couponList = couponService.findCouponEntities(parameters);
        List<NivoBubbleData> middleNivoBubbleDataList = new ArrayList<>();
        upperGrouping.apply(couponList).forEach((key, entity) -> {
            List<NivoBubbleData> nestedNivoBubbleDataList = lowerGrouping.apply(entity)
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(R::getOrderValue)))
                    .map(e -> new NivoBubbleData(e.getKey(), CouponGroupingUtils.sumGroup(e.getValue())))
                    .collect(Collectors.toList());

            middleNivoBubbleDataList.add(new NivoBubbleData(key, nestedNivoBubbleDataList));
        });
        middleNivoBubbleDataList.sort(java.util.Comparator.comparingInt(NivoBubbleData::getOrderValue));
        return new NivoBubbleData("Predaj kupónov", middleNivoBubbleDataList);
    }

    public List<NivoPieData> getPersonTypePieData(CouponsParameters parameters) {
        return abstractFetching(couponService.findByValidityAndGroupedByPersonType(parameters));
    }

    public List<NivoPieData> getMonthlyPieData(CouponsParameters parameters) {
        return abstractFetching(couponService.findByValidityAndGroupedByMonth(parameters));
    }

    public List<NivoPieData> getValidityPieData(CouponsParameters parameters) {
        return abstractFetching(couponService.findByValidityAndGroupedByValidity(parameters));
    }

    public List<NivoPieData> getSellTypePieData(CouponsParameters parameters) {
        return abstractFetching(couponService.findByValidityAndGroupedBySellType(parameters));
    }

    private <T extends IEnum> List<NivoPieData> abstractFetching(List<GroupingEntity<T>> grouping) {
        return grouping
                .stream()
                .map(e -> new NivoPieData(e.getKey().getValue(), e.getValue().intValue()))
                .collect(Collectors.toList());
    }

}
