package com.javapid.controller;

import com.javapid.entity.ApexchartsData;
import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.PidTicketsParameters;
import com.javapid.service.ApexchartsService;
import com.javapid.service.PidCouponsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apex")
public class ApexchartsController {

	private final ApexchartsService apexchartsService;
	private final PidCouponsService pidCouponsService;

	public ApexchartsController(ApexchartsService apexchartsService, PidCouponsService pidCouponsService) {
		this.apexchartsService = apexchartsService;
		this.pidCouponsService = pidCouponsService;
	}

	@RequestMapping(value = "/data")
	public List<ApexchartsData> getApexDataByPerson(@RequestParam(required = false) List<String> validity,
	                                                @RequestParam(required = false) List<String> type,
	                                                @RequestParam(required = false) List<String> month,
	                                                @RequestParam(required = false) List<String> year,
	                                                @RequestParam(required = false) List<String> person,
	                                                @RequestParam(required = false) String getBy) {
		PidCouponsParameters parameters = new PidCouponsParameters(validity, type, month, year, person);
		try {
			if ("person".equals(getBy.toLowerCase())) {
				return apexchartsService.getApexData(parameters);
			} else if ("validity".equals(getBy.toLowerCase())) {
				return pidCouponsService.getApexDataByValidity(parameters);
			}
			return apexchartsService.getApexData(parameters);
		} catch (Exception e) {
			return apexchartsService.getApexData(parameters);
		}
	}

	@RequestMapping(value = "/data/validity")
	public List<ApexchartsData> getApexDataByValidity(@RequestParam(required = false) List<String> validity,
	                                                  @RequestParam(required = false) List<String> type,
	                                                  @RequestParam(required = false) List<String> month,
	                                                  @RequestParam(required = false) List<String> year,
	                                                  @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getApexDataByValidity(new PidCouponsParameters(validity, type, month, year, person));
	}

	@RequestMapping(value = "/tickets/data")
	public List<ApexchartsData> getApexTicketData(@RequestParam(required = false) List<Boolean> discounted,
	                                              @RequestParam(required = false) List<String> month,
	                                              @RequestParam(required = false) List<String> year,
	                                              @RequestParam(required = false) List<String> ticket) {
		return apexchartsService.getApexTicketData(new PidTicketsParameters(month, year, discounted, ticket));
	}
}
