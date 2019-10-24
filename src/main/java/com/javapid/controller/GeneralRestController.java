package com.javapid.controller;

import com.javapid.entity.nivo.NivoBarData;
import com.javapid.service.NivoDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/general")
public class GeneralRestController {

	private final NivoDataService nivoService;

	public GeneralRestController(NivoDataService nivoService) {
		this.nivoService = nivoService;
	}

	@RequestMapping({"/area", "/bar"})
	public List<NivoBarData> getGeneralBarData(@RequestParam(required = false) List<String> validity,
	                                           @RequestParam(required = false) List<String> type,
	                                           @RequestParam(required = false) List<String> month,
	                                           @RequestParam(required = false) List<String> year) {
		return nivoService.getNivoBarData(validity, type, month, year);
	}

}
