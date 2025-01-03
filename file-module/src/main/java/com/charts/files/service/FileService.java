package com.charts.files.service;

import com.charts.general.repository.coupon.JpaCouponRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.charts.general.entity.coupon.CouponEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class FileService {

	private final JpaCouponRepository couponsRepository;

	public void saveDataFromFile(MultipartFile file) throws IOException {
		couponsRepository.save(getEmployeeFromJson(file));
	}

	private static CouponEntity getEmployeeFromJson(MultipartFile originalFile) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new String(originalFile.getBytes()), CouponEntity.class);
	}

}
