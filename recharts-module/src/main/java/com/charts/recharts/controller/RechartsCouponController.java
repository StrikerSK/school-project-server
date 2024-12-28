package com.charts.recharts.controller;

import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.api.ticket.entity.TicketsParameters;
import com.charts.recharts.entity.RechartsDataObject;
import com.charts.recharts.service.RechartsCouponService;
import com.charts.recharts.service.RechartsTicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recharts")
@AllArgsConstructor
public class RechartsCouponController {

	private final RechartsCouponService couponService;
	private final RechartsTicketService ticketService;

	@GetMapping(value = "/coupon", produces = "application/json")
	public List<List<RechartsDataObject>> getCouponData(
			@RequestParam(required = false) List<String> validity,
			@RequestParam(required = false) List<String> type,
			@RequestParam(required = false) List<String> month,
			@RequestParam(required = false) List<Integer> year,
			@RequestParam(required = false) List<String> person,
			@RequestParam String upperGroup,
			@RequestParam String lowerGroup
	) {
		return couponService.getCouponData(upperGroup, lowerGroup, new CouponsParameters(validity, type, month, year, person));
	}

	@GetMapping(value = "/ticket", produces = "application/json")
	public List<List<RechartsDataObject>> getMonthlyDataByTicketType(
			@RequestParam(required = false) List<Boolean> discounted,
			@RequestParam(required = false) List<String> month,
			@RequestParam(required = false) List<Integer> year,
			@RequestParam(required = false) List<String> ticket,
			@RequestParam String upperGroup,
			@RequestParam String lowerGroup
	) {
		return ticketService.getTicketData(upperGroup, lowerGroup, (new TicketsParameters(month, year, discounted, ticket)));
	}

}
