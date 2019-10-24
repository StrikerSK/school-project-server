package com.javapid.controller;

import com.javapid.entity.CouponEntity;
import com.javapid.entity.nivo.DataXY;
import com.javapid.repository.CouponRepository;
import com.javapid.service.PidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class PidRestController {

	private final PidService pidService;

	@Autowired
	private CouponRepository couponRepository;

	public PidRestController(PidService pidService) {
		this.pidService = pidService;
	}

//	@RequestMapping(name = "/getData")
//	public List<CouponEntity> getData() {
//		return pidService.getAllData();
//	}

	@RequestMapping("/uploadJson")
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		pidService.saveDataFromFile(file);
	}

	@RequestMapping("/getData/{code}")
	public List<CouponEntity> uploadFile(@PathVariable String code) {
		return pidService.getDataByCode(code);
	}

	@RequestMapping(name = "/test")
	public List<DataXY> testEndpoint() {
		return couponRepository.testTemplating("dospeli", Arrays.asList("Mesačná"));
	}

}
