package com.javapid.controller;

import com.javapid.entity.PidCouponsParameters;
import com.javapid.objects.recharts.PersonAbstractClass;
import com.javapid.service.PidCouponsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recharts")
public class RechartsController {

	private final PidCouponsService pidCouponsService;

	public RechartsController(PidCouponsService pidCouponsService) {
		this.pidCouponsService = pidCouponsService;
	}

	@RequestMapping({"/bar", "/pie", "/line", "/data"})
	public List<List<PersonAbstractClass>> getBarData(@RequestParam(required = false) List<String> validity,
	                                                  @RequestParam(required = false) List<String> type,
	                                                  @RequestParam(required = false) List<String> month,
	                                                  @RequestParam(required = false) List<String> year,
	                                                  @RequestParam(required = false) List<String> person) {
		return pidCouponsService.getRechartsData(new PidCouponsParameters(validity, type, month, year, person));
	}
}
