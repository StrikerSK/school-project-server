package com.charts.nivo.controller;

import com.charts.general.entity.parameters.CouponsParameters;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import com.charts.nivo.service.NivoCouponService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nivo")
public class NivoCouponController {

    private final NivoCouponService pidCouponsService;

    public NivoCouponController(NivoCouponService pidCouponsService) {
        this.pidCouponsService = pidCouponsService;
    }

    @RequestMapping({"/line", "/line/monthly/person"})
    public List<NivoLineData> getMonthlyDataByPersonType(@RequestParam(required = false) List<String> validity,
                                                         @RequestParam(required = false) List<String> type,
                                                         @RequestParam(required = false) List<String> month,
                                                         @RequestParam(required = false) List<Integer> year,
                                                         @RequestParam(required = false) List<String> person) {
        CouponsParameters parameters = new CouponsParameters(validity, type, month, year, person);
        return pidCouponsService.getMonthlyLineDataByPersonType(parameters);
    }

    @RequestMapping({"/line/monthly/validity"})
    public List<NivoLineData> getMonthlyDataByValidity(@RequestParam(required = false) List<String> validity,
                                                       @RequestParam(required = false) List<String> type,
                                                       @RequestParam(required = false) List<String> month,
                                                       @RequestParam(required = false) List<Integer> year,
                                                       @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getMonthlyLineDataByValidity(new CouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/line/sell", "/line/monthly/sell"})
    public List<NivoLineData> getMonthlyLineDataBySellType(@RequestParam(required = false) List<String> validity,
                                                           @RequestParam(required = false) List<String> type,
                                                           @RequestParam(required = false) List<String> month,
                                                           @RequestParam(required = false) List<Integer> year,
                                                           @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getMonthlyLineDataBySellType(new CouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/bar", "/general", "/bar/monthly/person"})
    public List<Map<String, Object>> getMonthlyBarDataByPersonType(@RequestParam(required = false) List<String> validity,
                                                                   @RequestParam(required = false) List<String> type,
                                                                   @RequestParam(required = false) List<String> month,
                                                                   @RequestParam(required = false) List<Integer> year,
                                                                   @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getMonthlyBarDataByPersonType(new CouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/bar/test", "/bar/monthly/validity"})
    public List<Map<String, Object>> getMonthlyBarDataByValidity(@RequestParam(required = false) List<String> validity,
                                                                 @RequestParam(required = false) List<String> type,
                                                                 @RequestParam(required = false) List<String> month,
                                                                 @RequestParam(required = false) List<Integer> year,
                                                                 @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getMonthlyBarDataByValidity(new CouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/bar/sell", "/bar/monthly/sell"})
    public List<Map<String, Object>> getMonthlyBarDataBySellType(@RequestParam(required = false) List<String> validity,
                                                                 @RequestParam(required = false) List<String> type,
                                                                 @RequestParam(required = false) List<String> month,
                                                                 @RequestParam(required = false) List<Integer> year,
                                                                 @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getMonthlyBarDataBySellType(new CouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/bubble", "/bubble/person/validity"})
    public NivoBubbleData getPersonBubbleDataByValidity(@RequestParam(required = false) List<String> validity,
                                                        @RequestParam(required = false) List<String> type,
                                                        @RequestParam(required = false) List<String> month,
                                                        @RequestParam(required = false) List<Integer> year,
                                                        @RequestParam(required = false) List<String> person) {
        CouponsParameters parameters = new CouponsParameters(validity, type, month, year, person);
        return pidCouponsService.getPersonBubbleDataByValidity(parameters);
    }

    @RequestMapping({"/bubble/person/sell"})
    public NivoBubbleData getPersonBubbleDataBySellType(@RequestParam(required = false) List<String> validity,
                                                        @RequestParam(required = false) List<String> type,
                                                        @RequestParam(required = false) List<String> month,
                                                        @RequestParam(required = false) List<Integer> year,
                                                        @RequestParam(required = false) List<String> person) {
        CouponsParameters parameters = new CouponsParameters(validity, type, month, year, person);
        return pidCouponsService.getPersonBubbleDataBySellType(parameters);
    }

    @RequestMapping({"/pie/person", "/waffle/person"})
    public List<NivoPieData> getPersonTypePieData(@RequestParam(required = false) List<String> validity,
                                                  @RequestParam(required = false) List<String> type,
                                                  @RequestParam(required = false) List<String> month,
                                                  @RequestParam(required = false) List<Integer> year,
                                                  @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getPersonTypePieData(new CouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/pie","/pie/month", "/waffle/month"})
    public List<NivoPieData> getMonthlyPieData(
            @RequestParam(required = false) List<String> validity,
            @RequestParam(required = false) List<String> type,
            @RequestParam(required = false) List<String> month,
            @RequestParam(required = false) List<Integer> year,
            @RequestParam(required = false) List<String> person
    ) {
        return pidCouponsService.getMonthlyPieData(new CouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/coupon/pie"})
    public NivoBubbleData getVariable(
            @RequestParam(required = false) List<String> validity,
            @RequestParam(required = false) List<String> type,
            @RequestParam(required = false) List<String> month,
            @RequestParam(required = false) List<Integer> year,
            @RequestParam(required = false) List<String> person,
            @RequestParam() String upperGroup,
            @RequestParam() String lowerGroup
    ) {
        return pidCouponsService.createDynamicBubbleData(upperGroup, lowerGroup, new CouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/pie/validity", "/waffle/validity"})
    public List<NivoPieData> getValidityPieData(@RequestParam(required = false) List<String> validity,
                                                @RequestParam(required = false) List<String> type,
                                                @RequestParam(required = false) List<String> month,
                                                @RequestParam(required = false) List<Integer> year,
                                                @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getValidityPieData(new CouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/pie/sell", "/waffle/sell"})
    public List<NivoPieData> getSellTypePieData(@RequestParam(required = false) List<String> validity,
                                                @RequestParam(required = false) List<String> type,
                                                @RequestParam(required = false) List<String> month,
                                                @RequestParam(required = false) List<Integer> year,
                                                @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getSellTypePieData(new CouponsParameters(validity, type, month, year, person));
    }
}
