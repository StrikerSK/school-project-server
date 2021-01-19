package com.charts.recharts.service;

import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;
import com.charts.recharts.entity.PersonAbstractClass;
import com.charts.general.service.PidCouponsService;
import com.charts.general.service.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RechartsCouponService {

	private final PidCouponsService pidCouponsService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RechartsCouponService.class);

	public RechartsCouponService(PidCouponsService pidCouponsService) {
		this.pidCouponsService = pidCouponsService;
	}

	/**
	 * Method fetches and adjust data to Recharts module
	 *
	 * @return data adapted to Recharts module
	 */
	public List<List<PersonAbstractClass>> getRechartsData(PidCouponsParameters parameters) {
		return pidCouponsService.getAllSumsRow(parameters).stream()
				.map(element -> createPersonList(element, parameters.getPerson()))
				.collect(Collectors.toList());
	}

	public List<PersonAbstractClass> createPersonList(NivoBarCouponDataByMonth data, List<String> personTypes) {
		List<PersonAbstractClass> personsList = new ArrayList<>();
		String month = data.getMonth();

		for (PersonType personType : PersonType.values()) {
			try {
				if (Validators.isPersonTypeRequested(personTypes, personType.value)) {
					String person = personType.methodValue;
					Long receivedValue = (Long) data.getClass().getMethod("get" + person).invoke(data);
					personsList.add(new PersonAbstractClass(personType.value, month, receivedValue));
				}
			} catch (Exception e) {
				LOGGER.error("There was an error!", e);
			}
		}
		return personsList;
	}

}
