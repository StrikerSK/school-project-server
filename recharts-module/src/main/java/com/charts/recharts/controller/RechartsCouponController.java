package com.charts.recharts.controller;

import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.recharts.entity.RechartsDataObject;
import com.charts.recharts.service.RechartsCouponService;
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

}
