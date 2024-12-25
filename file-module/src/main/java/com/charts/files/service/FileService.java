package com.charts.files.service;

import com.charts.api.coupon.repository.JpaCouponRepository;
import com.charts.api.coupon.service.CouponV2Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.charts.api.coupon.entity.v1.CouponEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class FileService {

	private final CouponV2Service couponService;

	private static CouponEntity getCouponsFle(MultipartFile originalFile) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new String(originalFile.getBytes()), CouponEntity.class);
	}

}
