package com.charts.nivo.service;

import com.charts.general.entity.GroupingEntity;
import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.api.coupon.utils.CouponGroupingUtils;
import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.general.entity.enums.IEnum;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoDataXY;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Comparator;

@Service
public class NivoCouponService {

    private final CouponV2Service couponService;

    public NivoCouponService(CouponV2Service couponService) {
        this.couponService = couponService;
    }

    public List<NivoLineData> getMonthlyLineDataByPersonType(CouponsParameters parameters) {
        return composeLineData(parameters, CouponGroupingUtils::groupByPersonType, CouponGroupingUtils::groupByMonth);
    }

    /**
     * Method gets line data by validity
     *
     * @param parameters all requested parameters
     * @return data for displaying line chart by validity
     */
    public List<NivoLineData> getMonthlyLineDataByValidity(CouponsParameters parameters) {
        return composeLineData(parameters, CouponGroupingUtils::groupByValidity, CouponGroupingUtils::groupByMonth);
    }

    /**
     * Method gets line data by validity
     *
     * @param parameters all requested parameters
     * @return data for displaying line chart by sell type
     */
    public List<NivoLineData> getMonthlyLineDataBySellType(CouponsParameters parameters) {
        return composeLineData(parameters, CouponGroupingUtils::groupBySellType, CouponGroupingUtils::groupByMonth);
    }

    /**
     * Method creates two level grouping. Level one grouping contains id and list of data that is composed by level two grouping.
     *
     * @param parameters all parameters obtained from request
     * @param upperGrouping top level grouping of data that which key value is used as 'id' parameter
     * @param nestedGrouping grouping of listed data in 'data' parameter
     */
    private <T extends IEnum, R extends IEnum> List<NivoLineData> composeLineData(
            CouponsParameters parameters,
            Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> upperGrouping,
            Function<List<UpdateCouponEntity>, Map<R, List<UpdateCouponEntity>>> nestedGrouping
    ) {
        List<UpdateCouponEntity> couponList = couponService.findCouponEntities(parameters);
        return upperGrouping.apply(couponList).entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(T::getOrderValue)))
                .map(e -> calculateLineData(e, nestedGrouping))
                .collect(Collectors.toList());
    }

    private  <T extends IEnum, R extends IEnum> NivoLineData calculateLineData(
            Map.Entry<T, List<UpdateCouponEntity>> mapEntry,
            Function<List<UpdateCouponEntity>, Map<R, List<UpdateCouponEntity>>> nestedGrouping
    ) {
        List<NivoDataXY> summarizedGroups = nestedGrouping.apply(mapEntry.getValue()).entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(R::getOrderValue)))
                .map(entry -> new NivoDataXY(entry.getKey(), CouponGroupingUtils.sumGroup(entry.getValue()).longValue()))
                .collect(Collectors.toList());
        return new NivoLineData(mapEntry.getKey(), summarizedGroups);
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
    * Method gets data for displaying bar chart. It is divided by two grouping upper grouping and nested grouping.
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
        middleNivoBubbleDataList.sort(Comparator.comparingInt(NivoBubbleData::getOrderValue));
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
                .map(e -> new NivoPieData(e.getKey().getValue(), e.getKey().getOrderValue(), e.getValue().intValue()))
                .collect(Collectors.toList());
    }

    public List<NivoPieData> createDynamicPieData(String groupName, CouponsParameters parameters) {
        List<NivoPieData> groupingFunction;

        if ("person".equals(groupName)) {
            groupingFunction = abstractFetching(couponService.findByValidityAndGroupedByPersonType(parameters));
        } else if ("month".equals(groupName)) {
            groupingFunction = abstractFetching(couponService.findByValidityAndGroupedByMonth(parameters));
        } else if ("sell".equals(groupName)) {
            groupingFunction = abstractFetching(couponService.findByValidityAndGroupedBySellType(parameters));
        } else if ("validity".equals(groupName)) {
            groupingFunction = abstractFetching(couponService.findByValidityAndGroupedByValidity(parameters));
        } else if ("year".equals(groupName)) {
            groupingFunction = abstractFetching(couponService.findByValidityAndGroupedByYear(parameters));
        } else {
            throw new IllegalArgumentException("Unknown group name: " + groupName);
        }

        groupingFunction.sort(Comparator.comparingInt(NivoPieData::getOrderValue));
        return groupingFunction;
    }

    public <T extends IEnum> List<NivoLineData> createDynamicLineData(String upperGroup, String lowerGroup, CouponsParameters parameters) {
        validateGroups(upperGroup, lowerGroup);
        Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> upperGroupingFunction = createGrouping(upperGroup);
        Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> lowerGroupingFunction = createGrouping(lowerGroup);
        return composeLineData(parameters, upperGroupingFunction, lowerGroupingFunction);
    }

    public <T extends IEnum> NivoBubbleData createDynamicBubbleData(String upperGroup, String lowerGroup, CouponsParameters parameters) {
        validateGroups(upperGroup, lowerGroup);
        Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> upperGroupingFunction = createGrouping(upperGroup);
        Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> lowerGroupingFunction = createGrouping(lowerGroup);
        return createBubbleData(parameters, upperGroupingFunction, lowerGroupingFunction);
    }

    private void validateGroups(String upperGroup, String lowerGroup) {
        if (upperGroup.equals(lowerGroup)) {
            throw new IllegalArgumentException("Cannot use same groups!");
        }
    }

    private <T> Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> createGrouping(String groupName) {
        Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> groupingFunction;

        switch (groupName) {
            case "person":
                groupingFunction = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByPersonType(e);
                break;
            case "month":
                groupingFunction = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByMonth(e);
                break;
            case "sell":
                groupingFunction = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupBySellType(e);
                break;
            case "validity":
                groupingFunction = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByValidity(e);
                break;
            case "year":
                groupingFunction = (e) -> (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByYear(e);
                break;
            default:
                throw new IllegalArgumentException("Unknown group name: " + groupName);
        }

        return groupingFunction;
    }

}
