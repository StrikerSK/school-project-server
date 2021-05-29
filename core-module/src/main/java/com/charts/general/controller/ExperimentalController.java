package com.charts.general.controller;

import com.charts.general.entity.nivo.bar.NivoBarMonthsDataByValidity;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.service.ICouponService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class ExperimentalController {
	private final ICouponService couponsService;

	public ExperimentalController(ICouponService couponsService) {
		this.couponsService = couponsService;
	}

	@RequestMapping("/validity/month")
	public List<NivoBarMonthsDataByValidity> getMonthDataByValidity(@RequestParam(required = false) List<String> validity,
	                                                                @RequestParam(required = false) List<String> type,
	                                                                @RequestParam(required = false) List<String> month,
	                                                                @RequestParam(required = false) List<String> year,
	                                                                @RequestParam(required = false) List<String> person) {
		return couponsService.getMonthsByValidity(new PidCouponsParameters(validity, type, month, year, person));
	}

}
