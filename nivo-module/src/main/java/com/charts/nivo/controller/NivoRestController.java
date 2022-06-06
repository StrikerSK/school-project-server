package com.charts.nivo.controller;

import com.charts.general.entity.PidCouponsParameters;
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
public class NivoRestController {

    private final NivoCouponService pidCouponsService;

    public NivoRestController(NivoCouponService pidCouponsService) {
        this.pidCouponsService = pidCouponsService;
    }

    @RequestMapping({"/line", "/line/monthly/person"})
    public List<NivoLineData> getMonthlyDataByPersonType(@RequestParam(required = false) List<String> validity,
                                                         @RequestParam(required = false) List<String> type,
                                                         @RequestParam(required = false) List<String> month,
                                                         @RequestParam(required = false) List<String> year,
                                                         @RequestParam(required = false) List<String> person) {
        PidCouponsParameters parameters = new PidCouponsParameters(validity, type, month, year, person);
        return pidCouponsService.getMonthlyLineDataByPersonType(parameters);
    }

    @RequestMapping({"/line/sell", "/line/monthly/validity"})
    public List<NivoLineData> getMonthlyDataByValidity(@RequestParam(required = false) List<String> validity,
                                                       @RequestParam(required = false) List<String> type,
                                                       @RequestParam(required = false) List<String> month,
                                                       @RequestParam(required = false) List<String> year,
                                                       @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getMonthlyLineDataByValidity(new PidCouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/bar", "/general", "/bar/monthly/person"})
    public List<Map<String, Object>> getMonthlyBarDataByPersonType(@RequestParam(required = false) List<String> validity,
                                                                   @RequestParam(required = false) List<String> type,
                                                                   @RequestParam(required = false) List<String> month,
                                                                   @RequestParam(required = false) List<String> year,
                                                                   @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getMonthlyBarDataByPersonType(new PidCouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/bar/test", "/bar/monthly/validity", "/bar/sell"})
    public Map<String, Map<String, Object>> getMonthlyBarDataByValidity(@RequestParam(required = false) List<String> validity,
                                                                        @RequestParam(required = false) List<String> type,
                                                                        @RequestParam(required = false) List<String> month,
                                                                        @RequestParam(required = false) List<String> year,
                                                                        @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getMonthlyBarDataByValidity(new PidCouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/bubble", "/bubble/person/validity"})
    public NivoBubbleData getPersonBubbleDataByValidity(@RequestParam(required = false) List<String> validity,
                                                        @RequestParam(required = false) List<String> type,
                                                        @RequestParam(required = false) List<String> month,
                                                        @RequestParam(required = false) List<String> year,
                                                        @RequestParam(required = false) List<String> person) {
        PidCouponsParameters parameters = new PidCouponsParameters(validity, type, month, year, person);
        return pidCouponsService.getPersonBubbleDataByValidity(parameters);
    }

    @RequestMapping({"/bubble/person/sell"})
    public NivoBubbleData getPersonBubbleDataBySellType(@RequestParam(required = false) List<String> validity,
                                                        @RequestParam(required = false) List<String> type,
                                                        @RequestParam(required = false) List<String> month,
                                                        @RequestParam(required = false) List<String> year,
                                                        @RequestParam(required = false) List<String> person) {
        PidCouponsParameters parameters = new PidCouponsParameters(validity, type, month, year, person);
        return pidCouponsService.getPersonBubbleDataBySellType(parameters);
    }

    @RequestMapping({"/pie/person", "/waffle/person"})
    public List<Map<String, Object>> getYearlyDataByPersonType(@RequestParam(required = false) List<String> validity,
                                                               @RequestParam(required = false) List<String> type,
                                                               @RequestParam(required = false) List<String> month,
                                                               @RequestParam(required = false) List<String> year,
                                                               @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getYearlyDataByPersonType(new PidCouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/pie/month", "/waffle/month"})
    public List<Map<String, Object>> getDataByYearAndValidityType(@RequestParam(required = false) List<String> validity,
                                                                  @RequestParam(required = false) List<String> type,
                                                                  @RequestParam(required = false) List<String> month,
                                                                  @RequestParam(required = false) List<String> year,
                                                                  @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getYearlyDataByMonth(new PidCouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/pie/validity", "/waffle/validity"})
    public List<NivoPieData> getValidityPieData(@RequestParam(required = false) List<String> validity,
                                                @RequestParam(required = false) List<String> type,
                                                @RequestParam(required = false) List<String> month,
                                                @RequestParam(required = false) List<String> year,
                                                @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getValidityPieData(new PidCouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/pie/sell", "/waffle/sell"})
    public List<NivoPieData> getSellTypePieData(@RequestParam(required = false) List<String> validity,
                                                @RequestParam(required = false) List<String> type,
                                                @RequestParam(required = false) List<String> month,
                                                @RequestParam(required = false) List<String> year,
                                                @RequestParam(required = false) List<String> person) {
        return pidCouponsService.getSellTypePieData(new PidCouponsParameters(validity, type, month, year, person));
    }
}
