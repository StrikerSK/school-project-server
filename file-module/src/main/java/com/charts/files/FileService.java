package com.charts.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.charts.general.entity.CouponEntity;
import com.charts.general.repository.PidCouponsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

	private final PidCouponsRepository couponsRepository;

	public FileService(PidCouponsRepository couponsRepository) {
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
