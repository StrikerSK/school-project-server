package com.charts.recharts.controller;

import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.recharts.entity.RechartsDataObject;
import com.charts.recharts.service.RechartsCouponService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recharts")
@AllArgsConstructor
public class RechartsCouponController {

	private final RechartsCouponService couponService;

	@RequestMapping({"/bar", "/pie", "/line", "/data", "/coupon"})
	public List<List<RechartsDataObject>> getMonthlyDataByPersonType(@RequestParam(required = false) List<String> validity,
																	 @RequestParam(required = false) List<String> type,
																	 @RequestParam(required = false) List<String> month,
																	 @RequestParam(required = false) List<Integer> year,
																	 @RequestParam(required = false) List<String> person,
																	 @RequestParam String upperGroup,
																	 @RequestParam String lowerGroup
	) {
		return couponService.getMonthlyDataByPersonType(upperGroup, lowerGroup, new CouponsParameters(validity, type, month, year, person));
	}

}
