package com.charts.apex.controller;

import com.charts.apex.entity.ApexObject;
import com.charts.apex.service.ApexCouponService;
import com.charts.general.entity.parameters.CouponsParameters;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apex")
@AllArgsConstructor
public class ApexCouponController {

	private final ApexCouponService apexCouponService;

	@RequestMapping(value = {"/data", "/monthly/person"})
	public List<ApexObject> getMonthlyDataByPersonType(@RequestParam(required = false) List<String> validity,
													   @RequestParam(required = false) List<String> type,
													   @RequestParam(required = false) List<String> month,
													   @RequestParam(required = false) List<Integer> year,
													   @RequestParam(required = false) List<String> person) {
		CouponsParameters parameters = new CouponsParameters(validity, type, month, year, person);
		return apexCouponService.getMonthlyDataByPersonType(parameters);
	}

	@RequestMapping(value = {"/data/validity", "/monthly/validity"})
	public List<ApexObject> getMonthlyDataByValidity(@RequestParam(required = false) List<String> validity,
													 @RequestParam(required = false) List<String> type,
													 @RequestParam(required = false) List<String> month,
													 @RequestParam(required = false) List<Integer> year,
													 @RequestParam(required = false) List<String> person) {
		return apexCouponService.getMonthlyDataByValidity(new CouponsParameters(validity, type, month, year, person));
	}

	@RequestMapping(value = {"/monthly/sell"})
	public List<ApexObject> getMonthlyDataBySellType(@RequestParam(required = false) List<String> validity,
													 @RequestParam(required = false) List<String> type,
													 @RequestParam(required = false) List<String> month,
													 @RequestParam(required = false) List<Integer> year,
													 @RequestParam(required = false) List<String> person) {
		return apexCouponService.getMonthlyDataBySellType(new CouponsParameters(validity, type, month, year, person));
	}

}
