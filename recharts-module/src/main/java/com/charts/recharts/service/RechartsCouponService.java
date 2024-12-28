package com.charts.recharts.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.enums.types.PersonType;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.api.coupon.utils.CouponGroupingUtils;
import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.api.coupon.entity.v2.UpdateCouponList;
import com.charts.api.coupon.repository.CouponRepository;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.types.Months;
import com.charts.recharts.entity.RechartsDataObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RechartsCouponService {

	private final CouponV2Service couponService;

	/**
	 * Method fetches and adjust data to Recharts module
	 *
	 * @return data adapted to Recharts module
	 */
	@SuppressWarnings("unchecked")
	public <T extends IEnum, R extends IEnum> List<List<RechartsDataObject>> getMonthlyDataByPersonType(CouponsParameters parameters) {
		List<UpdateCouponEntity> couponList = couponService.findCouponEntities(parameters);
		return CouponGroupingUtils.groupByPersonType(couponList)
				.entrySet()
				.stream()
				.map(upper -> {
					Map<T, List<UpdateCouponEntity>> nestedGrouping = (Map<T, List<UpdateCouponEntity>>) CouponGroupingUtils.groupByMonth(upper.getValue());
					return CouponGroupingUtils.sumGroup(nestedGrouping).entrySet()
							.stream()
							.map(lower -> new RechartsDataObject(upper.getKey(), lower.getKey(), lower.getValue().intValue()))
							.collect(Collectors.toList());
				})
				.collect(Collectors.toList());

//		parameters.getMonths().forEach(month -> {
//			List<RechartsDataObject> nestedList = new ArrayList<>();
//			UpdateCouponList entities = couponList.filterByMonth(Collections.singletonList(month));
//			parameters.getPersonTypeList().forEach(personType -> {
//				Integer monthlyValue = entities.filterByPersonType(Collections.singletonList(personType)).getCouponEntityList().stream()
//						.map(AbstractUpdateEntity::getValue)
//						.reduce(0, Integer::sum);
//				nestedList.add(new RechartsDataObject(personType, month, monthlyValue));
//			});
//			outputMapList.add(nestedList);
//		});
//		return outputMapList;
	}

}
