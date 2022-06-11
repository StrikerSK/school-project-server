package com.charts.general.service;

import com.charts.general.entity.coupon.CouponsParameters;
import com.charts.general.entity.coupon.updated.UpdateCouponEntity;
import com.charts.general.entity.enums.Validity;
import com.charts.general.repository.coupon.CouponRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CouponServiceImpl implements ICouponService {

	private final CouponRepository couponRepository;

	public Map<String, Integer> getAllData() {
		Map<String, Integer> output = new HashMap<>();

		couponRepository.getUpdateCouponList().getCouponEntityList().stream()
				.collect(Collectors.groupingBy(UpdateCouponEntity::getPersonType, Collectors.summingInt(UpdateCouponEntity::getValue)))
				.forEach((id, count) -> output.put(id.getValue(), count));

		return output;
	}

	public Map<String, Integer> getMonthlyDataByValidity(CouponsParameters parameters) {
		List<Validity> validityList = parameters.getValidity();

		Map<String, Integer> output = new HashMap<>();
		couponRepository.getUpdateCouponList()
				.filterByValidity(validityList)
				.getCouponEntityList().stream()
				.collect(Collectors.groupingBy(UpdateCouponEntity::getValidity, Collectors.summingInt(UpdateCouponEntity::getValue)))
				.forEach((id, count) -> output.put(id.getValue(), count));

		return output;
	}

}
