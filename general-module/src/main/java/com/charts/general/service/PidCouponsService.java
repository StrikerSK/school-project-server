package com.charts.general.service;

import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.nivo.bar.NivoBarCouponData;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;
import com.charts.general.entity.nivo.bar.NivoBarMonthsDataByValidity;
import com.charts.general.repository.PidCouponsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PidCouponsService {

	private final PidCouponsRepository pidCouponsRepository;
	private static final Logger LOGGER = Logger.getLogger("Coupon service");

	public PidCouponsService(PidCouponsRepository pidCouponsRepository) {
		this.pidCouponsRepository = pidCouponsRepository;
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
				if (Validators.isPersonTypeRequested(personTypes, personType.value)) {
					person = personType.methodValue;
					dataSum += (Long) element.getClass().getMethod("get" + person).invoke(element);
				}
			} catch (NoSuchMethodException e) {
				LOGGER.warning(String.format("There is no such method get%s()", person));
			} catch (Exception e) {
				LOGGER.warning("There was an error!");
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
		return pidCouponsRepository.getNivoBarDataByValidity(value, parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
	}

	public List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters) {
		return pidCouponsRepository.getNivoBarData(parameters.getValidity(), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
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

		return pidCouponsRepository.getNivoBarData(validity, sellType, month, year);
	}
}
