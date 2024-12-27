package com.charts.nivo.controller;

import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import com.charts.nivo.service.NivoCouponService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nivo/coupon")
@AllArgsConstructor
public class NivoCouponController {

    private final NivoCouponService nivoCouponService;

    @RequestMapping({"/bubble"})
    public NivoBubbleData getDynamicBubbleData(
            @RequestParam(required = false) List<String> validity,
            @RequestParam(required = false) List<String> type,
            @RequestParam(required = false) List<String> month,
            @RequestParam(required = false) List<Integer> year,
            @RequestParam(required = false) List<String> person,
            @RequestParam() String upperGroup,
            @RequestParam() String lowerGroup
    ) {
        return nivoCouponService.createDynamicBubbleData(upperGroup, lowerGroup, new CouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/line"})
    public List<NivoLineData> getDynamicLineData(
            @RequestParam(required = false) List<String> validity,
            @RequestParam(required = false) List<String> type,
            @RequestParam(required = false) List<String> month,
            @RequestParam(required = false) List<Integer> year,
            @RequestParam(required = false) List<String> person,
            @RequestParam() String upperGroup,
            @RequestParam() String lowerGroup
    ) {
        return nivoCouponService.createDynamicLineData(upperGroup, lowerGroup, new CouponsParameters(validity, type, month, year, person));
    }

    @RequestMapping({"/pie"})
    public List<NivoPieData> getDynamicPieData(
            @RequestParam(required = false) List<String> validity,
            @RequestParam(required = false) List<String> type,
            @RequestParam(required = false) List<String> month,
            @RequestParam(required = false) List<Integer> year,
            @RequestParam(required = false) List<String> person,
            @RequestParam() String group
    ) {
        return nivoCouponService.createDynamicPieData(group, new CouponsParameters(validity, type, month, year, person));
    }
}
