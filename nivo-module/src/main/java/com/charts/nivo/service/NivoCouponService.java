package com.charts.nivo.service;

import com.charts.general.entity.coupon.CouponsParameters;
import com.charts.general.entity.coupon.updated.UpdateCouponList;
import com.charts.general.entity.enums.SellType;
import com.charts.nivo.entity.NivoDataXY;
import com.charts.general.repository.coupon.CouponRepository;
import com.charts.general.utils.CouponGroupingUtils;
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
        CouponGroupingUtils.groupByMonth(couponList.getCouponEntityList())
                .forEach((month, entity) -> {
                    List<NivoDataXY> nestedData = new ArrayList<>();
                    CouponGroupingUtils.groupByAndSumByPerson(entity)
                            .forEach((validity, integer) -> nestedData.add(new NivoDataXY(validity, ((Integer) integer).longValue())));
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
        CouponGroupingUtils.groupByMonth(couponList.getCouponEntityList())
                .forEach((month, entity) -> {
                    List<NivoDataXY> nestedData = new ArrayList<>();
                    CouponGroupingUtils.groupByAndSumByValidity(entity)
                            .forEach((validity, integer) -> nestedData.add(new NivoDataXY(validity, ((Integer) integer).longValue())));
                    output.add(new NivoLineData(month, nestedData));
                });

        return output;
    }

    public List<NivoLineData> getMonthlyLineDataBySellType(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoLineData> output = new ArrayList<>();
        CouponGroupingUtils.groupByMonth(couponList.getCouponEntityList())
                .forEach((month, entity) -> {
                    List<NivoDataXY> nestedData = new ArrayList<>();
                    CouponGroupingUtils.groupByAndSumBySellType(entity)
                            .forEach((validity, integer) -> nestedData.add(new NivoDataXY(validity, ((Integer) integer).longValue())));
                    output.add(new NivoLineData(month, nestedData));
                });

        return output;
    }

    public List<Map<String, Object>> getMonthlyBarDataByPersonType(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<Map<String, Object>> outputMapList = new ArrayList<>();
        CouponGroupingUtils.groupByMonth(couponList.getCouponEntityList())
                .forEach((month, entities) -> {
                    Map<String, Object> tmpMap = new HashMap<>(CouponGroupingUtils.groupByAndSumByPerson(entities));
                    tmpMap.put("month", month);
                    outputMapList.add(tmpMap);
                });
        return outputMapList;
    }

    public List<Map<Object, Object>> getMonthlyBarDataByValidity(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<Map<Object, Object>> outputMapList = new ArrayList<>();
        CouponGroupingUtils.groupByMonth(couponList.getCouponEntityList())
                .forEach((month, entities) -> {
                    Map<Object, Object> tmpMap = new HashMap<>(CouponGroupingUtils.groupByAndSumByValidity(entities));
                    tmpMap.put("month", month);
                    outputMapList.add(tmpMap);
                });
        return outputMapList;
    }

    public List<Map<Object, Object>> getMonthlyBarDataBySellType(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<Map<Object, Object>> outputMapList = new ArrayList<>();
        CouponGroupingUtils.groupByMonth(couponList.getCouponEntityList())
                .forEach((month, entities) -> {
                    Map<Object, Object> tmpMap = new HashMap<>(CouponGroupingUtils.groupByAndSumBySellType(entities));
                    tmpMap.put("month", month);
                    outputMapList.add(tmpMap);
                });
        return outputMapList;
    }

    public NivoBubbleData getPersonBubbleDataByValidity(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoBubbleData> middleNivoBubbleDataList = new ArrayList<>();
        CouponGroupingUtils.groupByPersonType(couponList.getCouponEntityList()).forEach((key, entity) -> {
            List<NivoBubbleData> nestedNivoBubbleDataList = new ArrayList<>();
            CouponGroupingUtils.groupByAndSumByValidity(entity)
                    .forEach((validity, integer) -> nestedNivoBubbleDataList.add(new NivoBubbleData(validity, (Integer) integer)));
            middleNivoBubbleDataList.add(new NivoBubbleData(key.getValue(), nestedNivoBubbleDataList));
        });
        return new NivoBubbleData("Predaj kupónov", middleNivoBubbleDataList);
    }

    public NivoBubbleData getPersonBubbleDataBySellType(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoBubbleData> middleNivoBubbleDataList = new ArrayList<>();
        CouponGroupingUtils.groupByPersonType(couponList.getCouponEntityList()).forEach((key, entity) -> {
            List<NivoBubbleData> nestedNivoBubbleDataList = new ArrayList<>();
            CouponGroupingUtils.groupByAndSumBySellType(entity)
                    .forEach((validity, integer) -> nestedNivoBubbleDataList.add(new NivoBubbleData(validity, (Integer) integer)));
            middleNivoBubbleDataList.add(new NivoBubbleData(key.getValue(), nestedNivoBubbleDataList));
        });
        return new NivoBubbleData("Predaj kupónov", middleNivoBubbleDataList);
    }

    public List<NivoPieData> getPersonTypePieData(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoPieData> pieData = new ArrayList<>();
        CouponGroupingUtils.groupByAndSumByPerson(couponList.getCouponEntityList())
                .forEach((personType, total) -> pieData.add(new NivoPieData(personType, (Integer) total)));
        return pieData;
    }

    public List<NivoPieData> getMonthlyPieData(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoPieData> pieData = new ArrayList<>();
        CouponGroupingUtils.groupAndSumByMonth(couponList.getCouponEntityList())
                .forEach((month, total) -> pieData.add(new NivoPieData(month, ((Integer) total))));
        return pieData;
    }

    public List<NivoPieData> getValidityPieData(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoPieData> pieData = new ArrayList<>();
        CouponGroupingUtils.groupByAndSumByValidity(couponList.getCouponEntityList())
                .forEach((validity, total) -> pieData.add(new NivoPieData(validity, ((Integer) total))));
        return pieData;
    }

    public List<NivoPieData> getSellTypePieData(CouponsParameters parameters) {
        UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
        List<NivoPieData> pieData = new ArrayList<>();
        CouponGroupingUtils.groupByAndSumBySellType(couponList.getCouponEntityList())
                .forEach((sellType, total) -> pieData.add(new NivoPieData(sellType, ((Integer) total))));
        return pieData;
    }

}
