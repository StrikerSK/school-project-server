package com.javapid.controller;

import com.javapid.objects.recharts.AreaChartData;
import com.javapid.service.PidService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recharts")
public class RechartsController {

	private final PidService pidService;

	public RechartsController(PidService pidService) {
		this.pidService = pidService;
	}

	@RequestMapping("/area")
	public List<AreaChartData> getAreaChart(){
		return pidService.getAreaChartData();
	}

}
