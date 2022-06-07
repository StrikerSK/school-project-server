package com.charts.nivo.service;

import com.charts.general.entity.coupon.CouponsParameters;
import com.charts.general.entity.coupon.updated.UpdateCouponList;
import com.charts.general.entity.nivo.DataXY;
import com.charts.general.repository.coupon.CouponRepository;
import com.charts.general.utils.GroupingUtils;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NivoCouponService {

    private final CouponRepository couponRepository;

    public NivoCouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public List<NivoLineData> getMonthlyLineDataByPersonType(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoLineData> output = new ArrayList<>();
        GroupingUtils.groupByMonth(couponList.getCouponEntityList())
                .forEach((month, entity) -> {
                    List<DataXY> nestedData = new ArrayList<>();
                    GroupingUtils.groupByAndSumByPerson(entity)
                            .forEach((validity, integer) -> nestedData.add(new DataXY(validity, ((Integer) integer).longValue())));
                    output.add(new NivoLineData(month, nestedData));
                });
        return output;
    }

    /**
     * Method gets line data by validity
     *
     * @param parameters all requested parameters
     * @return data for displaying line chart by validity
     */
    public List<NivoLineData> getMonthlyLineDataByValidity(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoLineData> output = new ArrayList<>();
        GroupingUtils.groupByMonth(couponList.getCouponEntityList())
                .forEach((month, entity) -> {
                    List<DataXY> nestedData = new ArrayList<>();
                    GroupingUtils.groupByAndSumByValidity(entity)
                            .forEach((validity, integer) -> nestedData.add(new DataXY(validity, ((Integer) integer).longValue())));
                    output.add(new NivoLineData(month, nestedData));
                });

        return output;
    }

    public List<NivoPieData> getValidityPieData(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoPieData> pieData = new ArrayList<>();
        GroupingUtils.groupByAndSumByValidity(couponList.getCouponEntityList())
                .forEach((validity, total) -> pieData.add(new NivoPieData(validity, ((Integer) total).longValue())));
        return pieData;
    }

    public List<NivoPieData> getSellTypePieData(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoPieData> pieData = new ArrayList<>();
        GroupingUtils.groupByAndSumBySellType(couponList.getCouponEntityList())
                .forEach((validity, total) -> pieData.add(new NivoPieData(validity, ((Integer) total).longValue())));
        return pieData;
    }

    public List<Map<String, Object>> getMonthlyBarDataByPersonType(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<Map<String, Object>> outputMapList = new ArrayList<>();
        GroupingUtils.groupByMonth(couponList.getCouponEntityList())
                .forEach((month, entities) -> {
                    Map<String, Object> tmpMap = new HashMap<>(GroupingUtils.groupByAndSumByPerson(entities));
                    tmpMap.put("month", month);
                    outputMapList.add(tmpMap);
                });
        return outputMapList;
    }

    public NivoBubbleData getPersonBubbleDataByValidity(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoBubbleData> middleNivoBubbleDataList = new ArrayList<>();
        GroupingUtils.groupByPersonType(couponList.getCouponEntityList()).forEach((key, entity) -> {
            List<NivoBubbleData> nestedNivoBubbleDataList = new ArrayList<>();
            GroupingUtils.groupByAndSumByValidity(entity)
                    .forEach((validity, integer) -> nestedNivoBubbleDataList.add(new NivoBubbleData(validity, (Integer) integer)));
            middleNivoBubbleDataList.add(new NivoBubbleData(key.getValue(), nestedNivoBubbleDataList));
        });
        return new NivoBubbleData("Predaj kupónov", middleNivoBubbleDataList);
    }

    public NivoBubbleData getPersonBubbleDataBySellType(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoBubbleData> middleNivoBubbleDataList = new ArrayList<>();
        GroupingUtils.groupByPersonType(couponList.getCouponEntityList()).forEach((key, entity) -> {
            List<NivoBubbleData> nestedNivoBubbleDataList = new ArrayList<>();
            GroupingUtils.groupByAndSumBySellType(entity)
                    .forEach((validity, integer) -> nestedNivoBubbleDataList.add(new NivoBubbleData(validity, (Integer) integer)));
            middleNivoBubbleDataList.add(new NivoBubbleData(key.getValue(), nestedNivoBubbleDataList));
        });
        return new NivoBubbleData("Predaj kupónov", middleNivoBubbleDataList);
    }

    public Map<String, Map<String, Object>> getMonthlyBarDataByValidity(CouponsParameters parameters) {
        Map<String, Map<String, Object>> outputMap = new HashMap<>();
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        GroupingUtils.groupByMonth(couponList.getCouponEntityList()).forEach((key, entity) -> {
            Map<String, Object> validityMap = GroupingUtils.groupByAndSumByValidity(couponList.getCouponEntityList());
            outputMap.put(key, validityMap);
        });
        return outputMap;
    }

    public List<Map<String, Object>> getYearlyDataByPersonType(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<Map<String, Object>> mapList = new ArrayList<>();
        GroupingUtils.groupByYear(couponList.getCouponEntityList())
                .forEach((year, entity) -> {
                    Map<String, Object> tmpMap = GroupingUtils.groupByAndSumByPerson(entity);
                    tmpMap.put("year", year);
                    mapList.add(tmpMap);
                });
        return mapList;
    }

    public List<Map<String, Object>> getYearlyDataByMonth(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<Map<String, Object>> mapList = new ArrayList<>();
        GroupingUtils.groupByYear(couponList.getCouponEntityList())
                .forEach((year, entity) -> {
                    Map<String, Object> tmpMap = GroupingUtils.groupByAndSumByMonth(entity);
                    tmpMap.put("year", year);
                    mapList.add(tmpMap);
                });
        return mapList;
    }

}
