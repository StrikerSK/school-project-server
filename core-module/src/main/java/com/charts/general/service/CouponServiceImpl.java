package com.charts.general.service;

import com.charts.general.ClassMethodInvoker;
import com.charts.general.entity.CouponEntity;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.nivo.bar.NivoBarCouponData;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;
import com.charts.general.entity.nivo.bar.NivoBarMonthsDataByValidity;
import com.charts.general.repository.coupon.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class CouponServiceImpl implements ICouponService {

	private final CouponRepository couponRepository;

	public CouponServiceImpl(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}

	public List<CouponEntity> getAllData() {
		return couponRepository.findAll();
	}

	public List<CouponEntity> getDataByCode(String code) {
		return couponRepository.getByCode(code);
	}

	/**
	 * Method sums data row of provided line
	 *
	 * @param element     - represents line of retrieved data
	 * @param personTypes - represents requested person types
	 * @return sum of all values needed
	 */
	public Long getDataSum(NivoBarCouponData element, List<String> personTypes) {
		Long dataSum = 0L;
		String person = "";

		for (PersonType personType : PersonType.values()) {
			try {
				if (Validators.isPersonTypeRequested(personTypes, personType.getValue())) {
					person = personType.getMethodValue();
					dataSum += (Long) ClassMethodInvoker.invokeClassGetMethod(element, person);
				}
			} catch (Exception e) {
				log.warn("There was an error!");
			}
		}
		return dataSum;
	}

	public List<NivoBarMonthsDataByValidity> getMonthsByValidity(PidCouponsParameters parameters) {
		List<NivoBarMonthsDataByValidity> outputData = new ArrayList<>();
		for (String validity : parameters.getValidity()) {
			NivoBarMonthsDataByValidity outputObject = new NivoBarMonthsDataByValidity(validity);
			for (String month : parameters.getMonth()) {
				NivoBarCouponDataByMonth currentBarData = getAllSumsRow(parameters, Collections.singletonList(validity), null, Collections.singletonList(month), null).get(0);
				outputObject.setData(month, getDataSum(currentBarData, parameters.getPerson()));
			}
			outputData.add(outputObject);
		}
		return outputData;
	}

	public List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters, String value) {
		return couponRepository.getNivoBarDataByValidity(value, parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
	}

	public List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters) {
		return couponRepository.getNivoBarData(parameters.getValidity(), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
	}

	public List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters, List<String> validity, List<String> sellType, List<String> month, List<Integer> year) {

		if (validity == null) {
			validity = parameters.getValidity();
		}

		if (sellType == null) {
			sellType = parameters.getSellType();
		}

		if (month == null) {
			month = parameters.getMonth();
		}

		if (year == null) {
			year = parameters.getYearInteger();
		}

		return couponRepository.getNivoBarData(validity, sellType, month, year);
	}
}
