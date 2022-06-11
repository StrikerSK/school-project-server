package com.charts.recharts.service;

import com.charts.general.entity.coupon.CouponsParameters;
import com.charts.general.entity.coupon.updated.UpdateCouponList;
import com.charts.general.repository.coupon.CouponRepository;
import com.charts.recharts.entity.RechartsDataObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class RechartsCouponService {

	private final CouponRepository couponRepository;

	/**
	 * Method fetches and adjust data to Recharts module
	 *
	 * @return data adapted to Recharts module
	 */
	public List<List<RechartsDataObject>> getMonthlyDataByPersonType(CouponsParameters parameters) {
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<List<RechartsDataObject>> outputMapList = new ArrayList<>();
		parameters.getMonth().forEach(month -> {
			List<RechartsDataObject> nestedList = new ArrayList<>();
			UpdateCouponList entities = couponList.filterByMonth(Collections.singletonList(month));
			parameters.getPersonTypeList().forEach(personType -> {
				Long monthlyValue = entities.filterByPersonType(Collections.singletonList(personType)).getCouponEntityList().stream()
						.map(e -> e.getValue().longValue())
						.reduce(0L, Long::sum);
				nestedList.add(new RechartsDataObject(month, personType.getValue(), monthlyValue));
			});
			outputMapList.add(nestedList);
		});
		return outputMapList;
	}

}
