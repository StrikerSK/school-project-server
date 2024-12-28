package com.charts.apex.controller;

import com.charts.apex.entity.ApexObject;
import com.charts.apex.service.ApexCouponService;
import com.charts.api.coupon.entity.CouponsParameters;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apex")
@AllArgsConstructor
public class ApexCouponController {

	private final ApexCouponService apexCouponService;

	@GetMapping(value = "/coupon", produces = "application/json")
	public List<ApexObject> getMonthlyDataByPersonType(
			@RequestParam(required = false) List<String> validity,
			@RequestParam(required = false) List<String> type,
			@RequestParam(required = false) List<String> month,
			@RequestParam(required = false) List<Integer> year,
			@RequestParam(required = false) List<String> person,
			@RequestParam String upperGroup,
			@RequestParam String lowerGroup
	) {
		CouponsParameters parameters = new CouponsParameters(validity, type, month, year, person);
		return apexCouponService.getCouponData(upperGroup, lowerGroup, parameters);
	}

}
