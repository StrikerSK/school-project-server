package com.charts.recharts.controller;

import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.PidTicketsParameters;
import com.charts.general.objects.recharts.PersonAbstractClass;
import com.charts.recharts.service.RechartsCouponService;
import com.charts.recharts.service.RechartsTicketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recharts")
public class RechartsController {

	private final RechartsCouponService couponService;
	private final RechartsTicketService ticketService;

	public RechartsController(RechartsCouponService couponService, RechartsTicketService ticketService) {
		this.couponService = couponService;
		this.ticketService = ticketService;
	}

	@RequestMapping({"/bar", "/pie", "/line", "/data"})
	public List<List<PersonAbstractClass>> getBarData(@RequestParam(required = false) List<String> validity,
	                                                  @RequestParam(required = false) List<String> type,
	                                                  @RequestParam(required = false) List<String> month,
	                                                  @RequestParam(required = false) List<String> year,
	                                                  @RequestParam(required = false) List<String> person) {
		return couponService.getRechartsData(new PidCouponsParameters(validity, type, month, year, person));
	}

	@RequestMapping({"/tickets/bar", "/tickets/pie", "/tickets/line", "/tickets/data"})
	public List<List<PersonAbstractClass>> getRechartsTicketData(@RequestParam(required = false) List<Boolean> discounted,
	                                                             @RequestParam(required = false) List<String> month,
	                                                             @RequestParam(required = false) List<String> year,
	                                                             @RequestParam(required = false) List<String> ticket) {
		return ticketService.getRechartsTicketsData(new PidTicketsParameters(month, year, discounted, ticket));
	}
}
