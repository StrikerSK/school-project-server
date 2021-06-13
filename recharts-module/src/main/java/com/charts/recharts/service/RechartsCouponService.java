package com.charts.recharts.service;

import com.charts.general.ClassMethodInvoker;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;
import com.charts.general.service.ICouponService;
import com.charts.recharts.entity.PersonAbstractClass;
import com.charts.general.service.Validators;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RechartsCouponService {

	private final ICouponService couponService;

	public RechartsCouponService(ICouponService couponService) {
		this.couponService = couponService;
	}

	/**
	 * Method fetches and adjust data to Recharts module
	 *
	 * @return data adapted to Recharts module
	 */
	public List<List<PersonAbstractClass>> getRechartsData(PidCouponsParameters parameters) {
		return couponService.getAllSumsRow(parameters).stream()
				.map(element -> createPersonList(element, parameters.getPerson()))
				.collect(Collectors.toList());
	}

	private List<PersonAbstractClass> createPersonList(NivoBarCouponDataByMonth data, List<String> personTypes) {
		return PersonType.getList().stream()
				.filter(e -> Validators.isPersonTypeRequested(personTypes, e.getValue()))
				.map(e -> new PersonAbstractClass(e.getValue(), data.getMonth(), generateValue(e, data)))
				.collect(Collectors.toList());
	}

	@SneakyThrows
	private Long generateValue(PersonType personType, NivoBarCouponDataByMonth data){
		return (Long) ClassMethodInvoker.invokeClassGetMethod(data, personType.getMethodValue());
	}

}
