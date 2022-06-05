package com.charts.general.service;

import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.coupon.updated.UpdateCouponEntity;
import com.charts.general.entity.coupon.updated.UpdateCouponList;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.Validity;
import com.charts.general.entity.nivo.bar.NivoBarCouponData;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;
import com.charts.general.repository.coupon.CouponRepository;
import com.charts.general.repository.coupon.NewCouponRepository;
import com.charts.general.utils.ParameterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CouponServiceImpl implements ICouponService {

	private final CouponRepository couponRepository;

	private final NewCouponRepository newCouponRepository;

	public CouponServiceImpl(CouponRepository couponRepository, NewCouponRepository newCouponRepository) {
		this.couponRepository = couponRepository;
		this.newCouponRepository = newCouponRepository;
	}

	public Map<String, Integer> getAllData() {
		Map<String, Integer> output = new HashMap<>();

		newCouponRepository.getUpdateCouponList().getCouponEntityList().stream()
				.collect(Collectors.groupingBy(UpdateCouponEntity::getPersonType, Collectors.summingInt(UpdateCouponEntity::getValue)))
				.forEach((id, count) -> output.put(id.getValue(), count));

		return output;
	}

	/**
	 * Method sums data row of provided line
	 *
	 * @param element     - represents line of retrieved data
	 * @param personTypes - represents requested person types
	 * @return sum of all values needed
	 */
	public Long getDataSum(NivoBarCouponData element, List<String> personTypes) {
		UpdateCouponList couponEntityList = newCouponRepository.getUpdateCouponList();

		List<PersonType> pt = personTypes.stream()
				.map(PersonType::personTypeValue)
				.collect(Collectors.toList());

		return couponEntityList.filterByPersonType(pt).getCouponEntityList().stream()
				.map(e -> e.getValue().longValue())
				.reduce(0L, Long::sum);
	}

	public Map<String, Integer> getMonthlyDataByValidity(PidCouponsParameters parameters) {
		List<Validity> validityList = ParameterUtils.convertValidityList(parameters.getValidity());

		Map<String, Integer> output = new HashMap<>();
		newCouponRepository.getUpdateCouponList()
				.filterByValidity(validityList)
				.getCouponEntityList().stream()
				.collect(Collectors.groupingBy(UpdateCouponEntity::getValidity, Collectors.summingInt(UpdateCouponEntity::getValue)))
				.forEach((id, count) -> output.put(id.getValue(), count));

		return output;
	}

	public List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters, String value) {
		return couponRepository.getNivoBarDataByValidity(value, parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
	}

	public List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters) {
		return couponRepository.getNivoBarData(Arrays.asList(Validity.values()), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
	}

}
