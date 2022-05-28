package com.charts.general.controller;

import com.charts.general.entity.coupon.CouponEntity;
import com.charts.general.service.ICouponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

	@RequestMapping("/getData/{code}")
	public List<CouponEntity> uploadFile(@PathVariable String code) {
		return couponService.getDataByCode(code);
	}
}
