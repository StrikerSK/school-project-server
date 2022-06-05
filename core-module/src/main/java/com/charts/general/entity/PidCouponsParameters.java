package com.charts.general.entity;

import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import com.charts.general.utils.ParameterUtils;

import java.util.List;

import static com.charts.general.utils.ParameterUtils.verifyPersonList;
import static com.charts.general.utils.ParameterUtils.verifySellTypeList;
import static com.charts.general.utils.ParameterUtils.verifyValidityList;

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

	public List<Validity> getProcessedValidity() {
		return ParameterUtils.convertValidityList(validity);
	}

	public List<String> getSellType() {
		return verifySellTypeList(sellType);
	}

	public List<SellType> getProcessedSellType() {
		return ParameterUtils.convertSellTypeList(sellType);
	}

	public List<String> getPerson() {
		return verifyPersonList(person);
	}

	public List<PersonType> getProcessedPersonType() {
		return ParameterUtils.convertPersonList(person);
	}
}
