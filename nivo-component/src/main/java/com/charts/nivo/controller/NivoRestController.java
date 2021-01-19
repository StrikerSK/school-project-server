package com.charts.nivo.controller;

import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByValidity;
import com.charts.general.entity.nivo.bar.NivoBarValidityDataByMonth;
import com.charts.general.entity.nivo.bubble.NivoBubbleAbstract;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.nivo.NivoLineData;
import com.charts.general.entity.nivo.NivoPieData;
import com.charts.nivo.service.NivoCouponService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/nivo")
public class NivoRestController {

	private final NivoCouponService pidCouponsService;

	public NivoRestController(NivoCouponService pidCouponsService) {
		this.pidCouponsService = pidCouponsService;
	}

	@RequestMapping("/line")
	public List<NivoLineData> getData(@RequestParam(required = false) List<String> validity,
	                                  @RequestParam(required = false) List<String> type,
	                                  @RequestParam(required = false) List<String> month,
	                                  @RequestParam(required = false) List<String> year,
	                                  @RequestParam(required = false) List<String> person,
	                                  @RequestParam(required = false) String data) {
		PidCouponsParameters parameters = new PidCouponsParameters(validity, type, month, year, person);
		try {
			if ("sell".equalsIgnoreCase(data)) {
				return pidCouponsService.getNivoLineDataByValidity(parameters);
			}
			return pidCouponsService.getNivoLineData(parameters);
		} catch (NullPointerException e) {
			return pidCouponsService.getNivoLineData(parameters);
		}
	}

	@RequestMapping("/line/sell")
	public List<NivoLineData> getDataBySell(@RequestParam(required = false) List<String> type,
	                                        @RequestParam(required = false) List<String> month,
	                                        @RequestParam(required = false) List<String> year,
	                                        @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getNivoLineDataByValidity(new PidCouponsParameters(Collections.emptyList(), type, month, year, person));
	}

	@RequestMapping({"/bar","/general"})
	public List<NivoBarCouponDataByMonth> getGeneralBarData(@RequestParam(required = false) List<String> validity,
	                                                        @RequestParam(required = false) List<String> type,
	                                                        @RequestParam(required = false) List<String> month,
	                                                        @RequestParam(required = false) List<String> year,
	                                                        @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getNivoBarData(new PidCouponsParameters(validity, type, month, year, person));
	}

	@RequestMapping("/bar/test")
	public List<NivoBarValidityDataByMonth> testMethod(@RequestParam(required = false) List<String> validity,
	                                                   @RequestParam(required = false) List<String> type,
	                                                   @RequestParam(required = false) List<String> month,
	                                                   @RequestParam(required = false) List<String> year,
	                                                   @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getValidityByMonth(new PidCouponsParameters(validity, type, month, year, person));
	}

	@RequestMapping("/bubble")
	public NivoBubbleAbstract getBubbleData(@RequestParam(required = false) List<String> validity,
	                                        @RequestParam(required = false) List<String> type,
	                                        @RequestParam(required = false) List<String> month,
	                                        @RequestParam(required = false) List<String> year,
	                                        @RequestParam(required = false) List<String> person,
	                                        @RequestParam(required = false) String data) {
		PidCouponsParameters parameters = new PidCouponsParameters(validity, type, month, year, person);
		try {
			if ("validity".equalsIgnoreCase(data)) {
				return pidCouponsService.getNivoBubbleChartByValidity(parameters);
			} else if ("complex".equalsIgnoreCase(data)) {
				return pidCouponsService.getNivoBubbleChartExperimental(parameters);
			}
			return pidCouponsService.getNivoBubbleChart(parameters);
		} catch (NullPointerException e) {
			return pidCouponsService.getNivoBubbleChart(parameters);
		}
	}

	@RequestMapping("/bar/sell")
	public List<NivoBarCouponDataByValidity> retrieveBarDataByValidity(@RequestParam(required = false) List<String> type,
	                                                                   @RequestParam(required = false) List<String> month,
	                                                                   @RequestParam(required = false) List<String> year,
	                                                                   @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getNivoBarDataByValidity(new PidCouponsParameters(Collections.emptyList(), type, month, year, person));
	}

	@RequestMapping({"/pie", "/waffle"})
	public List<NivoPieData> retrievePieData(@RequestParam(required = false) List<String> validity,
	                                         @RequestParam(required = false) List<String> type,
	                                         @RequestParam(required = false) List<String> month,
	                                         @RequestParam(required = false) List<String> year,
	                                         @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getNivoPieData(new PidCouponsParameters(validity, type, month, year, person));
	}

	@RequestMapping({"/pie/sell", "/waffle/sell"})
	public List<NivoPieData> retrievePieDataByValidity(@RequestParam(required = false) List<String> type,
	                                                   @RequestParam(required = false) List<String> month,
	                                                   @RequestParam(required = false) List<String> year,
	                                                   @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getNivoPieDataByValidity(new PidCouponsParameters(Collections.emptyList(), type, month, year, person));
	}
}
