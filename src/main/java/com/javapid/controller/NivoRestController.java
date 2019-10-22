package com.javapid.controller;

import com.javapid.entity.nivo.NivoJizdenkyBarData;
import com.javapid.entity.nivo.line.NivoGeneralLineData;
import com.javapid.entity.nivo.line.NivoLineAbstractData;
import com.javapid.entity.nivo.NivoBarData;
import com.javapid.entity.nivo.pie.NivoPieAbstractData;
import com.javapid.service.NivoDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("nivo")
public class NivoRestController {

	private final NivoDataService nivoDataService;

	public NivoRestController(NivoDataService nivoDataService) {
		this.nivoDataService = nivoDataService;
	}

	@RequestMapping("line")
	public List<NivoLineAbstractData> getData(@RequestParam(required = false) List<String> validity,
	                                          @RequestParam(required = false) List<String> type,
	                                          @RequestParam(required = false) List<String> month,
	                                          @RequestParam(required = false) List<String> year,
	                                          @RequestParam(required = false) List<String> person) {
		return nivoDataService.getNivoLineData(validity, type, month, year, person);
	}

	@RequestMapping("line/sell")
	public List<NivoGeneralLineData> getDataBySell(@RequestParam(required = false) List<String> type,
	                                               @RequestParam(required = false) List<String> month,
	                                               @RequestParam(required = false) List<String> year,
	                                               @RequestParam(required = false) List<String> person) {
		return nivoDataService.getNivoLineDataByValidity(type, month, year, person);
	}

	@RequestMapping("bar")
	public List<NivoBarData> retrieveBarData(@RequestParam(required = false) List<String> validity,
	                                         @RequestParam(required = false) List<String> type,
	                                         @RequestParam(required = false) List<String> month,
	                                         @RequestParam(required = false) List<String> year) {
		return nivoDataService.getNivoBarData(validity, type, month, year);
	}

	@RequestMapping({"pie", "waffle"})
	public List<NivoPieAbstractData> retrievePieData(@RequestParam(required = false) List<String> validity,
	                                                 @RequestParam(required = false) List<String> type,
	                                                 @RequestParam(required = false) List<String> month,
	                                                 @RequestParam(required = false) List<String> year,
	                                                 @RequestParam(required = false) List<String> person) {
		return nivoDataService.getNivoPieData(validity, type, month, year, person);
	}

	@RequestMapping("/tickets/line")
	public List<NivoLineAbstractData> getData(@RequestParam(required = false) List<Boolean> discounted,
	                                          @RequestParam(required = false) List<String> month,
	                                          @RequestParam(required = false) List<String> year,
	                                          @RequestParam(required = false) List<String> ticket) {
		return nivoDataService.getJizdenyLineData(discounted, month, year, ticket);
	}

	@RequestMapping("/tickets/bar")
	public List<NivoJizdenkyBarData> retrieveBarData(@RequestParam(required = false) List<Boolean> discounted,
	                                                 @RequestParam(required = false) List<String> month,
	                                                 @RequestParam(required = false) List<String> year) {
		return nivoDataService.getJizdenkyBarData(discounted, month, year);
	}

	@RequestMapping({"/tickets/pie", "/tickets/waffle"})
	public List<NivoPieAbstractData> retrievePieData(@RequestParam(required = false) List<Boolean> discounted,
	                                                 @RequestParam(required = false) List<String> month,
	                                                 @RequestParam(required = false) List<String> year,
	                                                 @RequestParam(required = false) List<String> ticket) {
		return nivoDataService.getJizdenkyPieData(discounted, month, year, ticket);
	}
}
