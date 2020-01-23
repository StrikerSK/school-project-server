package com.javapid.controller;

import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.PidTicketsParameters;
import com.javapid.objects.recharts.PersonAbstractClass;
import com.javapid.service.PidCouponsService;
import com.javapid.service.PidTicketsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recharts")
public class RechartsController {

	private final PidCouponsService pidCouponsService;
	private final PidTicketsService pidTicketsService;

	public RechartsController(PidCouponsService pidCouponsService, PidTicketsService pidTicketsService) {
		this.pidCouponsService = pidCouponsService;
		this.pidTicketsService = pidTicketsService;
	}

	@RequestMapping({"/bar", "/pie", "/line", "/data"})
	public List<List<PersonAbstractClass>> getBarData(@RequestParam(required = false) List<String> validity,
	                                                  @RequestParam(required = false) List<String> type,
	                                                  @RequestParam(required = false) List<String> month,
	                                                  @RequestParam(required = false) List<String> year,
	                                                  @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getRechartsData(new PidCouponsParameters(validity, type, month, year, person));
	}

	@RequestMapping({"/tickets/bar", "/tickets/pie", "/tickets/line", "/tickets/data"})
	public List<List<PersonAbstractClass>> getRechartsTicketData(@RequestParam(required = false) List<Boolean> discounted,
	                                                             @RequestParam(required = false) List<String> month,
	                                                             @RequestParam(required = false) List<String> year,
	                                                             @RequestParam(required = false) List<String> ticket) {
		return pidTicketsService.getRechartsTicketsData(new PidTicketsParameters(month, year, discounted, ticket));
	}
}
