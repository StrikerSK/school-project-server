package com.javapid.controller;

import com.javapid.entity.CouponEntity;
import com.javapid.service.PidService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

	@RequestMapping("/uploadJson")
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		pidService.saveDataFromFile(file);
	}

	@RequestMapping("/getData/{code}")
	public List<CouponEntity> uploadFile(@PathVariable String code) {
		return pidService.getDataByCode(code);
	}

}
