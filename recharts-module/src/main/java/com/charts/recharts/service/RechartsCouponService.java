package com.charts.recharts.service;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.coupon.CouponsParameters;
import com.charts.general.entity.coupon.v2.CouponListV2;
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
		CouponListV2 couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<List<RechartsDataObject>> outputMapList = new ArrayList<>();
		parameters.getMonths().forEach(month -> {
			List<RechartsDataObject> nestedList = new ArrayList<>();
			CouponListV2 entities = couponList.filterByMonth(Collections.singletonList(month));
			parameters.getPersonTypeList().forEach(personType -> {
				Integer monthlyValue = entities.filterByPersonType(Collections.singletonList(personType)).getCouponEntityList().stream()
						.map(AbstractUpdateEntity::getValue)
						.reduce(0, Integer::sum);
				nestedList.add(new RechartsDataObject(personType, month, monthlyValue));
			});
			outputMapList.add(nestedList);
		});
		return outputMapList;
	}

}
