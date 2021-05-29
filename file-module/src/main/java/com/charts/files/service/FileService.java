package com.charts.files.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.charts.general.entity.CouponEntity;
import com.charts.general.repository.coupon.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

	private final CouponRepository couponsRepository;

	public FileService(CouponRepository couponsRepository) {
		this.couponsRepository = couponsRepository;
	}

	public void saveDataFromFile(MultipartFile file) throws IOException {
		couponsRepository.save(getEmployeeFromJson(file));
	}

	private static CouponEntity getEmployeeFromJson(MultipartFile originalFile) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new String(originalFile.getBytes()), CouponEntity.class);
	}

}
