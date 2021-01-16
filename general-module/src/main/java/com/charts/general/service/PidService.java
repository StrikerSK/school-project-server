package com.charts.general.service;

import com.charts.general.entity.CouponEntity;
import com.charts.general.repository.PidCouponsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PidService {

	private final PidCouponsRepository pidCouponsRepository;

	public PidService(PidCouponsRepository pidCouponsRepository) {
		this.pidCouponsRepository = pidCouponsRepository;
	}

	public List<CouponEntity> getAllData() {
		return pidCouponsRepository.findAll();
	}

	public List<CouponEntity> getDataByCode(String code) {
		return pidCouponsRepository.getByCode(code);
	}
}
