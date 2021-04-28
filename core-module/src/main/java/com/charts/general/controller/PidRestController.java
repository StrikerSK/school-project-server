package com.charts.general.controller;

import com.charts.general.entity.CouponEntity;
import com.charts.general.service.PidService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PidRestController {

	private final PidService pidService;

	public PidRestController(PidService pidService) {
		this.pidService = pidService;
	}

	@RequestMapping(name = "/getData")
	public List<CouponEntity> getData() {
		return pidService.getAllData();
	}

	@RequestMapping("/getData/{code}")
	public List<CouponEntity> uploadFile(@PathVariable String code) {
		return pidService.getDataByCode(code);
	}
}
