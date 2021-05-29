package com.charts.general.controller;

import com.charts.general.entity.CouponEntity;
import com.charts.general.service.ICouponService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PidRestController {

	private final ICouponService couponService;

	public PidRestController(ICouponService couponService) {
		this.couponService = couponService;
	}

	@RequestMapping(name = "/getData")
	public List<CouponEntity> getData() {
		return couponService.getAllData();
	}

	@RequestMapping("/getData/{code}")
	public List<CouponEntity> uploadFile(@PathVariable String code) {
		return couponService.getDataByCode(code);
	}
}
