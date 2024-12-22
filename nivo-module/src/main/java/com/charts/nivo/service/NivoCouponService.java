package com.charts.nivo.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.api.coupon.utils.CouponGroupingUtils;
import com.charts.general.entity.parameters.CouponsParameters;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.Months;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoDataXY;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NivoCouponService {

    private final CouponV2Service couponService;

    public NivoCouponService(CouponV2Service couponService) {
        this.couponService = couponService;
    }

    public List<NivoLineData> getMonthlyLineDataByPersonType(CouponsParameters parameters) {
        List<UpdateCouponEntity> couponList = couponService.findCouponEntities(parameters);
        return CouponGroupingUtils.groupByPersonType(couponList).entrySet()
                .stream()
                .map(NivoCouponService::apply)
                .collect(Collectors.toList());
    }

    /**
     * Method gets line data by validity
     *
     * @param parameters all requested parameters
     * @return data for displaying line chart by validity
     */
    public List<NivoLineData> getMonthlyLineDataByValidity(CouponsParameters parameters) {
        List<UpdateCouponEntity> couponList = couponService.findCouponEntities(parameters);
        return CouponGroupingUtils.groupByValidity(couponList).entrySet()
                .stream()
                .map(NivoCouponService::apply)
                .collect(Collectors.toList());
    }

    /**
     * Method gets line data by validity
     *
     * @param parameters all requested parameters
     * @return data for displaying line chart by sell type
     */
    public List<NivoLineData> getMonthlyLineDataBySellType(CouponsParameters parameters) {
        List<UpdateCouponEntity> couponList = couponService.findCouponEntities(parameters);
        return CouponGroupingUtils.groupBySellType(couponList).entrySet()
                .stream()
                .map(NivoCouponService::apply)
                .collect(Collectors.toList());
    }

    private static <T extends IEnum> NivoLineData apply(Map.Entry<T, List<UpdateCouponEntity>> e) {
        List<NivoDataXY> summarizedGroups = CouponGroupingUtils.groupByMonth(e.getValue()).entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Months::getOrderValue)))
                .map(entry -> new NivoDataXY(entry.getKey(), CouponGroupingUtils.sumGroup(entry.getValue()).longValue()))
                .collect(Collectors.toList());
        return new NivoLineData(e.getKey(), summarizedGroups);
    }

    public List<Map<String, Object>> getMonthlyBarDataByPersonType(CouponsParameters parameters) {
        return createTwoLevelGrouping(parameters, CouponGroupingUtils::groupByMonth, CouponGroupingUtils::groupByPersonType);
    }

    public List<Map<String, Object>> getMonthlyBarDataByValidity(CouponsParameters parameters) {
        return createTwoLevelGrouping(parameters, CouponGroupingUtils::groupByMonth, CouponGroupingUtils::groupByValidity);
    }

    public List<Map<String, Object>> getMonthlyBarDataBySellType(CouponsParameters parameters) {
        return createTwoLevelGrouping(parameters, CouponGroupingUtils::groupByMonth, CouponGroupingUtils::groupBySellType);
    }

    /**
    * Method gets data for displaying bubble chart. It is divided by two grouping upper grouping and nested grouping.
    *
    * @param parameters all parameters obtained from request
    * @param upperGrouping top level grouping of data
    * @param nestedGrouping nested grouping of withing upper grouping
    */
    private <T extends IEnum, R extends IEnum> List<Map<String, Object>> createTwoLevelGrouping(
            CouponsParameters parameters,
            Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> upperGrouping,
            Function<List<UpdateCouponEntity>, Map<R, List<UpdateCouponEntity>>> nestedGrouping
    ) {
        List<UpdateCouponEntity> couponList = couponService.findCouponEntities(parameters);
        List<Map<String, Object>> outputMapList = new ArrayList<>();
        upperGrouping.apply(couponList)
                .forEach((month, entities) -> {
                    Map<String, Object> outputMap = nestedGrouping.apply(entities)
                            .entrySet()
                            .stream()
                            .map(e -> new AbstractMap.SimpleEntry<>(e.getKey().getValue(), CouponGroupingUtils.sumGroup(e.getValue())))
                            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
                    outputMap.put("month", month.getValue());
                    outputMap.put("label", month.getValue());
                    outputMapList.add(outputMap);
                });
        return outputMapList;
    }

    public NivoBubbleData getPersonBubbleDataByValidity(CouponsParameters parameters) {
        List<UpdateCouponEntity> couponList = couponService.findCouponEntities(parameters);
        List<NivoBubbleData> middleNivoBubbleDataList = new ArrayList<>();
        CouponGroupingUtils.groupByPersonType(couponList).forEach((key, entity) -> {
            List<NivoBubbleData> nestedNivoBubbleDataList = CouponGroupingUtils.groupByValidity(entity)
                    .entrySet()
                    .stream()
                    .map(e -> new NivoBubbleData(e.getKey(), CouponGroupingUtils.sumGroup(e.getValue())))
                    .collect(Collectors.toList());

            middleNivoBubbleDataList.add(new NivoBubbleData(key.getValue(), nestedNivoBubbleDataList));
        });
        return new NivoBubbleData("Predaj kupónov", middleNivoBubbleDataList);
    }

    public NivoBubbleData getPersonBubbleDataBySellType(CouponsParameters parameters) {
        List<UpdateCouponEntity> couponList = couponService.findCouponEntities(parameters);
        List<NivoBubbleData> middleNivoBubbleDataList = new ArrayList<>();
        CouponGroupingUtils.groupByPersonType(couponList).forEach((key, entity) -> {
            List<NivoBubbleData> nestedNivoBubbleDataList = CouponGroupingUtils.groupBySellType(entity)
                    .entrySet()
                    .stream()
                    .map(e -> new NivoBubbleData(e.getKey(), CouponGroupingUtils.sumGroup(e.getValue())))
                    .collect(Collectors.toList());

            middleNivoBubbleDataList.add(new NivoBubbleData(key.getValue(), nestedNivoBubbleDataList));
        });
        return new NivoBubbleData("Predaj kupónov", middleNivoBubbleDataList);
    }

    public List<NivoPieData> getPersonTypePieData(CouponsParameters parameters) {
        return couponService.findByValidityAndGroupedByPersonType(parameters)
                .stream()
                .map(e -> new NivoPieData(e.getKey().getValue(), e.getValue().intValue()))
                .collect(Collectors.toList());
    }

    public List<NivoPieData> getMonthlyPieData(CouponsParameters parameters) {
        return couponService.findByValidityAndGroupedByMonth(parameters)
                .stream()
                .map(e -> new NivoPieData(e.getKey().getValue(), e.getValue().intValue()))
                .collect(Collectors.toList());
    }

    public List<NivoPieData> getValidityPieData(CouponsParameters parameters) {
        return couponService.findByValidityAndGroupedByValidity(parameters)
                .stream()
                .map(e -> new NivoPieData(e.getKey().getValue(), e.getValue().intValue()))
                .collect(Collectors.toList());
    }

    public List<NivoPieData> getSellTypePieData(CouponsParameters parameters) {
        return couponService.findByValidityAndGroupedBySellType(parameters)
                .stream()
                .map(e -> new NivoPieData(e.getKey().getValue(), e.getValue().intValue()))
                .collect(Collectors.toList());
    }

}
