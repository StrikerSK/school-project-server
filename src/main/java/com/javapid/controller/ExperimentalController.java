package com.javapid.controller;

import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.nivo.bar.NivoBarDataMonthsByValidity;
import com.javapid.service.PidCouponsService;
import com.javapid.service.PidTicketsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class ExperimentalController {

	private final PidTicketsService ticketsService;

	private final PidCouponsService couponsService;

	public ExperimentalController(PidTicketsService ticketsService, PidCouponsService couponsService) {
		this.ticketsService = ticketsService;
		this.couponsService = couponsService;
	}

	@RequestMapping("/validity/month")
	public List<NivoBarDataMonthsByValidity> getMonthDataByValidity(@RequestParam(required = false) List<String> validity,
	                                                                @RequestParam(required = false) List<String> type,
	                                                                @RequestParam(required = false) List<String> month,
	                                                                @RequestParam(required = false) List<String> year,
	                                                                @RequestParam(required = false) List<String> person) {
		return couponsService.getMonthsByValidity(new PidCouponsParameters(validity, type, month, year, person));
	}

}
