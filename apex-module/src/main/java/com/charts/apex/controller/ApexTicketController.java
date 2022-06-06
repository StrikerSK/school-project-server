package com.charts.apex.controller;

import com.charts.apex.entity.ApexObject;
import com.charts.apex.service.ApexTicketService;
import com.charts.general.entity.PidTicketsParameters;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apex")
@AllArgsConstructor
public class ApexTicketController {
	private final ApexTicketService apexTicketService;

	@RequestMapping(value = "/tickets/data")
	public List<ApexObject> getApexTicketData(@RequestParam(required = false) List<Boolean> discounted,
											  @RequestParam(required = false) List<String> month,
											  @RequestParam(required = false) List<String> year,
											  @RequestParam(required = false) List<String> ticket) {
		return apexTicketService.getApexTicketData(new PidTicketsParameters(month, year, discounted, ticket));
	}

}
