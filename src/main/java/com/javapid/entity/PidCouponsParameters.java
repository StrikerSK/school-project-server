package com.javapid.entity;

import java.util.List;

import static com.javapid.service.Validators.*;

public class PidCouponsParameters extends PidAbstractParameters {

	private List<String> validity;
	private List<String> sellType;
	private List<String> person;

	public PidCouponsParameters(List<String> validity, List<String> sellType, List<String> month, List<String> year, List<String> person) {
		super(month,year);
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
