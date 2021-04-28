package com.charts.nivo.controller;

import com.charts.general.entity.PidTicketsParameters;
import com.charts.general.entity.nivo.NivoLineData;
import com.charts.general.entity.nivo.NivoPieData;
import com.charts.general.entity.nivo.bar.NivoBarTicketsDAOByMonth;
import com.charts.nivo.service.NivoTicketsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/nivo")
public class NivoTickerController {

	private final NivoTicketsService nivoTicketsService;

	public NivoTickerController(NivoTicketsService nivoTicketsService) {
		this.nivoTicketsService = nivoTicketsService;
	}

	@RequestMapping("/tickets/line")
	public List<NivoLineData> getData(@RequestParam(required = false) List<Boolean> discounted,
	                                  @RequestParam(required = false) List<String> month,
	                                  @RequestParam(required = false) List<String> year,
	                                  @RequestParam(required = false) List<String> ticket) {
		return nivoTicketsService.getTicketsLineData(new PidTicketsParameters(month, year, discounted, ticket));
	}

	@RequestMapping("/tickets/bar")
	public List<NivoBarTicketsDAOByMonth> retrieveBarData(@RequestParam(required = false) List<Boolean> discounted,
	                                                      @RequestParam(required = false) List<String> month,
	                                                      @RequestParam(required = false) List<String> year) {
		return nivoTicketsService.getTicketBarData(new PidTicketsParameters(month, year, discounted, Collections.emptyList()));
	}

	@RequestMapping({"/tickets/pie", "/tickets/waffle"})
	public List<NivoPieData> retrievePieData(@RequestParam(required = false) List<Boolean> discounted,
	                                         @RequestParam(required = false) List<String> month,
	                                         @RequestParam(required = false) List<String> year,
	                                         @RequestParam(required = false) List<String> ticket) {
		return nivoTicketsService.getTicketsPieData(new PidTicketsParameters(month, year, discounted, ticket));
	}
}
