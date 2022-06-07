package com.charts.apex.service;

import com.charts.apex.entity.ApexObject;
import com.charts.general.entity.coupon.CouponsParameters;
import com.charts.general.entity.coupon.updated.UpdateCouponList;
import com.charts.general.repository.coupon.CouponRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ApexCouponService {
	private final CouponRepository couponRepository;

	public List<ApexObject> getMonthlyDataByPersonType(final CouponsParameters parameters) {
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<ApexObject> outputMapList = new ArrayList<>();
		parameters.getProcessedPersonType().forEach(personType -> {
			ApexObject apexObject = new ApexObject(personType.getValue());
			List<Long> values = new ArrayList<>();
			UpdateCouponList entities = couponList.filterByPersonType(Collections.singletonList(personType));
			parameters.getMonth().forEach(month -> {
				Long monthlyValue = entities.filterByMonth(Collections.singletonList(month)).getCouponEntityList().stream()
						.map(e -> e.getValue().longValue())
						.reduce(0L, Long::sum);
				values.add(monthlyValue);
			});
			outputMapList.add(apexObject.withList(values));
		});
		return outputMapList;
	}

	/**
	 * Method retrieves Apex data by validity
	 */
	public List<ApexObject> getMonthlyDataByValidity(CouponsParameters parameters) {
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<ApexObject> outputMapList = new ArrayList<>();
		parameters.getProcessedValidity().forEach(validity -> {
			ApexObject apexObject = new ApexObject(validity.getValue());
			List<Long> values = new ArrayList<>();
			UpdateCouponList entities = couponList.filterByValidity(Collections.singletonList(validity));
			parameters.getMonth().forEach(month -> {
				Long monthlyValue = entities.filterByMonth(Collections.singletonList(month)).getCouponEntityList().stream()
						.map(e -> e.getValue().longValue())
						.reduce(0L, Long::sum);
				values.add(monthlyValue);
			});
			outputMapList.add(apexObject.withList(values));
		});
		return outputMapList;
	}

	public List<ApexObject> getMonthlyDataBySellType(CouponsParameters parameters) {
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<ApexObject> outputMapList = new ArrayList<>();
		parameters.getProcessedSellType().forEach(sellType -> {
			ApexObject apexObject = new ApexObject(sellType.getValue());
			List<Long> values = new ArrayList<>();
			UpdateCouponList entities = couponList.filterBySellType(Collections.singletonList(sellType));
			parameters.getMonth().forEach(month -> {
				Long monthlyValue = entities.filterByMonth(Collections.singletonList(month)).getCouponEntityList().stream()
						.map(e -> e.getValue().longValue())
						.reduce(0L, Long::sum);
				values.add(monthlyValue);
			});
			outputMapList.add(apexObject.withList(values));
		});
		return outputMapList;
	}

}
