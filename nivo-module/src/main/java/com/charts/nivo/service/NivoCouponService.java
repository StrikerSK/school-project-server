package com.charts.nivo.service;

import com.charts.api.coupon.service.CouponV2Service;
import com.charts.api.coupon.utils.CouponGroupingUtils;
import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.nivo.Utils.NivoConvertersUtils;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return NivoConvertersUtils.createBubbleData(
                couponService.findCouponEntities(parameters),
                CouponGroupingUtils::groupByPersonType,
                CouponGroupingUtils::groupByValidity,
                CouponGroupingUtils::aggregateGroupSum
        );
    }

    public NivoBubbleData getPersonBubbleDataBySellType(CouponsParameters parameters) {
        return NivoConvertersUtils.createBubbleData(
                couponService.findCouponEntities(parameters),
                CouponGroupingUtils::groupByPersonType,
                CouponGroupingUtils::groupBySellType,
                CouponGroupingUtils::aggregateGroupSum
        );
    }

    public List<NivoPieData> getPersonTypePieData(CouponsParameters parameters) {
        return NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedByPersonType(parameters));
    }

    public List<NivoPieData> getMonthlyPieData(CouponsParameters parameters) {
        return NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedByMonth(parameters));
    }

    public List<NivoPieData> getValidityPieData(CouponsParameters parameters) {
        return NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedByValidity(parameters));
    }

    public List<NivoPieData> getSellTypePieData(CouponsParameters parameters) {
        return NivoConvertersUtils.createPieData(couponService.findByValidityAndGroupedBySellType(parameters));
    }

}
