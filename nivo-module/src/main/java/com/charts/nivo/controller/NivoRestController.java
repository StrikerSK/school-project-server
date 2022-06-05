package com.charts.nivo.controller;

import com.charts.general.entity.enums.Validity;
import com.charts.general.entity.nivo.BubbleData;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.nivo.NivoLineData;
import com.charts.general.entity.nivo.NivoPieData;
import com.charts.nivo.service.NivoCouponService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
		return pidCouponsService.getNivoLineData(parameters);
	}

	@RequestMapping("/line/sell")
	public List<NivoLineData> getDataBySell(@RequestParam(required = false) List<String> type,
	                                        @RequestParam(required = false) List<String> month,
	                                        @RequestParam(required = false) List<String> year,
	                                        @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getValidityDataByMonth(new PidCouponsParameters(Collections.emptyList(), type, month, year, person));
	}

	@RequestMapping({"/bar","/general"})
	public List<Map<String, Object>> getGeneralBarData(@RequestParam(required = false) List<String> validity,
	                                                        @RequestParam(required = false) List<String> type,
	                                                        @RequestParam(required = false) List<String> month,
	                                                        @RequestParam(required = false) List<String> year,
	                                                        @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getPersonDataByMonthBar(new PidCouponsParameters(validity, type, month, year, person));
	}

	@RequestMapping("/bar/test")
	public Map<String, Map<Validity, Integer>> testMethod(@RequestParam(required = false) List<String> validity,
														  @RequestParam(required = false) List<String> type,
														  @RequestParam(required = false) List<String> month,
														  @RequestParam(required = false) List<String> year,
														  @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getValidityByMonth(new PidCouponsParameters(validity, type, month, year, person));
	}

	@RequestMapping("/bubble")
	public BubbleData getBubbleData(@RequestParam(required = false) List<String> validity,
									@RequestParam(required = false) List<String> type,
									@RequestParam(required = false) List<String> month,
									@RequestParam(required = false) List<String> year,
									@RequestParam(required = false) List<String> person,
									@RequestParam(required = false) String data) {
		PidCouponsParameters parameters = new PidCouponsParameters(validity, type, month, year, person);
		return pidCouponsService.getBubbleChartData(parameters);
	}

	@RequestMapping("/bar/sell")
	public List<Map<String, Object>> retrieveBarDataByValidity(@RequestParam(required = false) List<String> type,
	                                                                   @RequestParam(required = false) List<String> month,
	                                                                   @RequestParam(required = false) List<String> year,
	                                                                   @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getNivoBarDataByValidity(new PidCouponsParameters(Collections.emptyList(), type, month, year, person));
	}

	@RequestMapping({"/pie/person", "/waffle/person"})
	public List<Map<String, Object>> getDataByYearAndPersonType(@RequestParam(required = false) List<String> validity,
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

	@RequestMapping({"/pie/sell", "/waffle/sell"})
	public List<NivoPieData> retrievePieDataByValidity(@RequestParam(required = false) List<String> type,
	                                                   @RequestParam(required = false) List<String> month,
	                                                   @RequestParam(required = false) List<String> year,
	                                                   @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getValidityData(new PidCouponsParameters(Collections.emptyList(), type, month, year, person));
	}
}
