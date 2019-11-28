package com.javapid.controller;

import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.apexcharts.ApexchartsAreaData;
import com.javapid.service.ApexchartsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apex")
public class ApexchartsController {

	private final ApexchartsService apexchartsService;

	public ApexchartsController(ApexchartsService apexchartsService) {
		this.apexchartsService = apexchartsService;
	}

	@RequestMapping("/area")
	public List<ApexchartsAreaData> getMonthDataByValidity(@RequestParam(required = false) List<String> validity,
	                                                       @RequestParam(required = false) List<String> type,
	                                                       @RequestParam(required = false) List<String> month,
	                                                       @RequestParam(required = false) List<String> year,
	                                                       @RequestParam(required = false) List<String> person) {
		return apexchartsService.getAreaData(new PidCouponsParameters(validity, type, month, year, person));
	}
}
