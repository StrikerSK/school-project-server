package com.charts.recharts.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.api.coupon.utils.CouponFunctionUtils;
import com.charts.api.coupon.utils.CouponGroupingUtils;
import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.general.entity.enums.IEnum;
import com.charts.recharts.entity.RechartsDataObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
	public <T extends IEnum, R extends IEnum> List<List<RechartsDataObject>> getMonthlyDataByPersonType(
			String upperGroup,
			String lowerGroup,
			CouponsParameters parameters
	) {
		CouponFunctionUtils.validateGroups(upperGroup, lowerGroup);

		Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> upperFunction = CouponFunctionUtils.createGrouping(upperGroup);
		Function<List<UpdateCouponEntity>, Map<T, List<UpdateCouponEntity>>> lowerFunction = CouponFunctionUtils.createGrouping(lowerGroup);

		List<UpdateCouponEntity> couponList = couponService.findCouponEntities(parameters);
		return upperFunction.apply(couponList)
				.entrySet()
				.stream()
				.sorted(Comparator.comparing(e -> e.getKey().getOrderValue()))
				.map(upper -> {
					Map<T, List<UpdateCouponEntity>> nestedGrouping = lowerFunction.apply(upper.getValue());
					return CouponGroupingUtils.sumGroup(nestedGrouping).entrySet()
							.stream()
							.sorted(Comparator.comparing(e -> e.getKey().getOrderValue()))
							.map(lower -> new RechartsDataObject(upper.getKey(), lower.getKey(), lower.getValue().intValue()))
							.collect(Collectors.toList());
				})
				.collect(Collectors.toList());
	}

}
