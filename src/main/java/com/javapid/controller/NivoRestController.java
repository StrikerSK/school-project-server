package com.javapid.controller;

import com.javapid.entity.nivo.NivoBarData;
import com.javapid.entity.nivo.NivoJizdenkyBarData;
import com.javapid.entity.nivo.line.NivoGeneralLineData;
import com.javapid.entity.nivo.line.NivoLineAbstractData;
import com.javapid.entity.nivo.pie.NivoGeneralPieData;
import com.javapid.entity.nivo.pie.NivoPieAbstractData;
import com.javapid.service.PidCouponsService;
import com.javapid.service.PidTicketsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public List<NivoLineAbstractData> getData(@RequestParam(required = false) List<String> validity,
	                                          @RequestParam(required = false) List<String> type,
	                                          @RequestParam(required = false) List<String> month,
	                                          @RequestParam(required = false) List<String> year,
	                                          @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getNivoLineData(validity, type, month, year, person);
	}

	@RequestMapping("/line/sell")
	public List<NivoGeneralLineData> getDataBySell(@RequestParam(required = false) List<String> type,
	                                               @RequestParam(required = false) List<String> month,
	                                               @RequestParam(required = false) List<String> year,
	                                               @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getNivoLineDataByValidity(type, month, year, person);
	}

	@RequestMapping("/bar")
	public List<NivoBarData> getGeneralBarData(@RequestParam(required = false) List<String> validity,
	                                           @RequestParam(required = false) List<String> type,
	                                           @RequestParam(required = false) List<String> month,
	                                           @RequestParam(required = false) List<String> year) {
		return pidCouponsService.getNivoBarData(validity, type, month, year);
	}

	@RequestMapping("/bar/sell")
	public List<NivoGeneralPieData> retrieveBarDataByValidity(@RequestParam(required = false) List<String> type,
	                                                          @RequestParam(required = false) List<String> month,
	                                                          @RequestParam(required = false) List<String> year,
	                                                          @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getNivoPieDataByValidity(type, month, year, person);
	}

	@RequestMapping({"/pie", "/waffle"})
	public List<NivoPieAbstractData> retrievePieData(@RequestParam(required = false) List<String> validity,
	                                                 @RequestParam(required = false) List<String> type,
	                                                 @RequestParam(required = false) List<String> month,
	                                                 @RequestParam(required = false) List<String> year,
	                                                 @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getNivoPieData(validity, type, month, year, person);
	}

	@RequestMapping({"/pie/sell", "/waffle/sell"})
	public List<NivoGeneralPieData> retrievePieDataByValidity(@RequestParam(required = false) List<String> validity,
	                                                          @RequestParam(required = false) List<String> type,
	                                                          @RequestParam(required = false) List<String> month,
	                                                          @RequestParam(required = false) List<String> year,
	                                                          @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getNivoPieDataByValidity(type, month, year, person);
	}

	@RequestMapping("/tickets/line")
	public List<NivoLineAbstractData> getData(@RequestParam(required = false) List<Boolean> discounted,
	                                          @RequestParam(required = false) List<String> month,
	                                          @RequestParam(required = false) List<String> year,
	                                          @RequestParam(required = false) List<String> ticket) {
		return pidTicketsService.getJizdenyLineData(discounted, month, year, ticket);
	}

	@RequestMapping("/tickets/bar")
	public List<NivoJizdenkyBarData> retrieveBarData(@RequestParam(required = false) List<Boolean> discounted,
	                                                 @RequestParam(required = false) List<String> month,
	                                                 @RequestParam(required = false) List<String> year) {
		return pidTicketsService.getTicketBarData(discounted, month, year);
	}

	@RequestMapping({"/tickets/pie", "/tickets/waffle"})
	public List<NivoPieAbstractData> retrievePieData(@RequestParam(required = false) List<Boolean> discounted,
	                                                 @RequestParam(required = false) List<String> month,
	                                                 @RequestParam(required = false) List<String> year,
	                                                 @RequestParam(required = false) List<String> ticket) {
		return pidTicketsService.getTicketsPieData(discounted, month, year, ticket);
	}
}
