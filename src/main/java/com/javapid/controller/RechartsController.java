package com.javapid.controller;

import com.javapid.entity.nivo.NivoBarData;
import com.javapid.objects.recharts.PersonAbstractClass;
import com.javapid.service.RechartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recharts")
public class RechartsController {

	@Autowired
	private RechartsService rechartsService;

	@RequestMapping("/area")
	public List<NivoBarData> getAreaChart(@RequestParam(required = false) List<String> validity,
	                                      @RequestParam(required = false) List<String> type,
	                                      @RequestParam(required = false) List<String> month) {
		return rechartsService.getAreaChartData(validity, type, month);
	}

	@RequestMapping({"/bar", "/pie"})
	public List<List<PersonAbstractClass>> getBarData(@RequestParam(required = false) List<String> validity,
	                                                  @RequestParam(required = false) List<String> type,
	                                                  @RequestParam(required = false) List<String> month) {
		return rechartsService.getPersonData(validity, type, month);
	}
}
