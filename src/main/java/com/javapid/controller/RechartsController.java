package com.javapid.controller;

import com.javapid.entity.nivo.NivoBarData;
import com.javapid.objects.recharts.PersonAbstractClass;
import com.javapid.service.NivoDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recharts")
public class RechartsController {

	private final NivoDataService nivoDataService;

	public RechartsController(NivoDataService nivoDataService) {
		this.nivoDataService = nivoDataService;
	}

	@RequestMapping("/area")
	public List<NivoBarData> getAreaChart(@RequestParam(required = false) List<String> validity,
	                                      @RequestParam(required = false) List<String> type,
	                                      @RequestParam(required = false) List<String> month,
	                                      @RequestParam(required = false) List<String> year) {
		return nivoDataService.getAreaChartData(validity, type, month, year);
	}

	@RequestMapping({"/bar", "/pie", "/line"})
	public List<List<PersonAbstractClass>> getBarData(@RequestParam(required = false) List<String> validity,
	                                                  @RequestParam(required = false) List<String> type,
	                                                  @RequestParam(required = false) List<String> month,
	                                                  @RequestParam(required = false) List<String> year) {
		return nivoDataService.getPersonData(validity, type, month, year);
	}
}
