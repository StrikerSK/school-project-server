package com.charts.general.controller;

import com.charts.general.service.ICouponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("")
public class PidRestController {

	private final ICouponService couponService;
	public PidRestController(ICouponService couponService) {
		this.couponService = couponService;
	}

	@GetMapping("/getData")
	public Map<String, Integer> getData() {
		return couponService.getAllData();
	}

}
