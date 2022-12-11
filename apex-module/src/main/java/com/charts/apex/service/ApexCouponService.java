package com.charts.apex.service;

import com.charts.apex.entity.ApexObject;
import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.coupon.CouponsParameters;
import com.charts.general.entity.coupon.v2.CouponListV2;
import com.charts.general.entity.enums.Months;
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
		CouponListV2 couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<ApexObject> outputMapList = new ArrayList<>();
		parameters.getPersonTypeList().forEach(personType -> {
			ApexObject apexObject = new ApexObject(personType.getValue());
			CouponListV2 filteredList = couponList.filterByPersonType(Collections.singletonList(personType));
			List<Integer> values = mapMonth(filteredList, parameters.getMonths());
			outputMapList.add(apexObject.withList(values));
		});
		return outputMapList;
	}

	/**
	 * Method retrieves Apex data by validity
	 */
	public List<ApexObject> getMonthlyDataByValidity(CouponsParameters parameters) {
		CouponListV2 couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<ApexObject> outputMapList = new ArrayList<>();
		parameters.getValidity().forEach(validity -> {
			ApexObject apexObject = new ApexObject(validity.getValue());
			CouponListV2 filteredList = couponList.filterByValidity(Collections.singletonList(validity));
			List<Integer> values = mapMonth(filteredList, parameters.getMonths());
			outputMapList.add(apexObject.withList(values));
		});
		return outputMapList;
	}

	public List<ApexObject> getMonthlyDataBySellType(CouponsParameters parameters) {
		CouponListV2 couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<ApexObject> outputMapList = new ArrayList<>();
		parameters.getSellTypes().forEach(sellType -> {
			ApexObject apexObject = new ApexObject(sellType.getValue());
			CouponListV2 filteredList = couponList.filterBySellType(Collections.singletonList(sellType));
			List<Integer> values = mapMonth(filteredList, parameters.getMonths());
			outputMapList.add(apexObject.withList(values));
		});
		return outputMapList;
	}

	private List<Integer> mapMonth(CouponListV2 couponList, List<Months> months) {
		List<Integer> values = new ArrayList<>();
		months.forEach(month -> {
			Integer monthlyValue = couponList.filterByMonth(Collections.singletonList(month)).getCouponEntityList().stream()
					.map(AbstractUpdateEntity::getValue)
					.reduce(0, Integer::sum);
			values.add(monthlyValue);
		});
		return values;
	}

}
