package com.charts.general.controller;

import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByValidity;
import com.charts.general.entity.nivo.bar.NivoBarTicketsDAOByMonth;
import com.charts.general.entity.nivo.bar.NivoBarValidityDataByMonth;
import com.charts.general.entity.nivo.bubble.NivoBubbleAbstract;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.PidTicketsParameters;
import com.charts.general.entity.nivo.NivoLineData;
import com.charts.general.entity.nivo.NivoPieData;
import com.charts.general.service.PidCouponsService;
import com.charts.general.service.PidTicketsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/nivo")
public class NivoRestController {

	private final PidCouponsService pidCouponsService;
	private final PidTicketsService pidTicketsService;

	public NivoRestController(PidCouponsService pidCouponsService, PidTicketsService pidTicketsService) {
		this.pidCouponsService = pidCouponsService;
		this.pidTicketsService = pidTicketsService;
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

	@RequestMapping("/bar")
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

	@RequestMapping("/tickets/line")
	public List<NivoLineData> getData(@RequestParam(required = false) List<Boolean> discounted,
	                                  @RequestParam(required = false) List<String> month,
	                                  @RequestParam(required = false) List<String> year,
	                                  @RequestParam(required = false) List<String> ticket) {
		return pidTicketsService.getTicketsLineData(new PidTicketsParameters(month, year, discounted, ticket));
	}

	@RequestMapping("/tickets/bar")
	public List<NivoBarTicketsDAOByMonth> retrieveBarData(@RequestParam(required = false) List<Boolean> discounted,
	                                                      @RequestParam(required = false) List<String> month,
	                                                      @RequestParam(required = false) List<String> year) {
		return pidTicketsService.getTicketBarData(new PidTicketsParameters(month, year, discounted, Collections.emptyList()));
	}

	@RequestMapping({"/tickets/pie", "/tickets/waffle"})
	public List<NivoPieData> retrievePieData(@RequestParam(required = false) List<Boolean> discounted,
	                                         @RequestParam(required = false) List<String> month,
	                                         @RequestParam(required = false) List<String> year,
	                                         @RequestParam(required = false) List<String> ticket) {
		return pidTicketsService.getTicketsPieData(new PidTicketsParameters(month, year, discounted, ticket));
	}
}
