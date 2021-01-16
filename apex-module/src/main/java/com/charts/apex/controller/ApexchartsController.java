package com.charts.apex.controller;

import com.charts.apex.entity.ApexchartsObject;
import com.charts.apex.service.ApexchartsService;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.PidTicketsParameters;
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

	@RequestMapping(value = "/data")
	public List<ApexchartsObject> getApexDataByPerson(@RequestParam(required = false) List<String> validity,
	                                                  @RequestParam(required = false) List<String> type,
	                                                  @RequestParam(required = false) List<String> month,
	                                                  @RequestParam(required = false) List<String> year,
	                                                  @RequestParam(required = false) List<String> person,
	                                                  @RequestParam(required = false) String getBy) {
		PidCouponsParameters parameters = new PidCouponsParameters(validity, type, month, year, person);
		try {
			if ("person".equalsIgnoreCase(getBy)) {
				return apexchartsService.getApexData(parameters);
			} else if ("validity".equalsIgnoreCase(getBy)) {
				return apexchartsService.getApexDataByValidity(parameters);
			}
			return apexchartsService.getApexData(parameters);
		} catch (Exception e) {
			return apexchartsService.getApexData(parameters);
		}
	}

	@RequestMapping(value = "/data/validity")
	public List<ApexchartsObject> getApexDataByValidity(@RequestParam(required = false) List<String> validity,
	                                                    @RequestParam(required = false) List<String> type,
	                                                    @RequestParam(required = false) List<String> month,
	                                                    @RequestParam(required = false) List<String> year,
	                                                    @RequestParam(required = false) List<String> person) {
		return apexchartsService.getApexDataByValidity(new PidCouponsParameters(validity, type, month, year, person));
	}

	@RequestMapping(value = "/tickets/data")
	public List<ApexchartsObject> getApexTicketData(@RequestParam(required = false) List<Boolean> discounted,
	                                                @RequestParam(required = false) List<String> month,
	                                                @RequestParam(required = false) List<String> year,
	                                                @RequestParam(required = false) List<String> ticket) {
		return apexchartsService.getApexTicketData(new PidTicketsParameters(month, year, discounted, ticket));
	}
}
