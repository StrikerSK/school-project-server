package com.charts.general.entity;

import java.util.List;

import static com.charts.general.service.Validators.verifyPersonList;
import static com.charts.general.service.Validators.verifySellTypeList;
import static com.charts.general.service.Validators.verifyValidityList;

public class PidCouponsParameters extends PidAbstractParameters {

	private final List<String> validity;
	private final List<String> sellType;
	private final List<String> person;

	public PidCouponsParameters(List<String> validity, List<String> sellType, List<String> month, List<String> year, List<String> person) {
		super(month, year);
		this.validity = validity;
		this.sellType = sellType;
		this.person = person;
	}

	public List<String> getValidity() {
		return verifyValidityList(validity);
	}

	public List<String> getSellType() {
		return verifySellTypeList(sellType);
	}

	public List<String> getPerson() {
		return verifyPersonList(person);
	}
}
