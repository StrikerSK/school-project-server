package com.javapid.controller;

import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.nivo.bar.NivoBarDataByMonth;
import com.javapid.service.PidCouponsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/general")
public class GeneralRestController {

	private final PidCouponsService nivoService;

	public GeneralRestController(PidCouponsService nivoService) {
		this.nivoService = nivoService;
	}

	@RequestMapping({"/area", "/bar"})
	public List<NivoBarDataByMonth> getGeneralBarData(@RequestParam(required = false) List<String> validity,
	                                                  @RequestParam(required = false) List<String> type,
	                                                  @RequestParam(required = false) List<String> month,
	                                                  @RequestParam(required = false) List<String> year,
	                                                  @RequestParam(required = false) List<String> person) {
		return nivoService.getNivoBarData(new PidCouponsParameters(validity, type, month, year, person));
	}

}
