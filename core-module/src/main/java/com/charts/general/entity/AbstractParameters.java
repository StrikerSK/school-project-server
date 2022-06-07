package com.charts.general.entity;

import java.util.List;

import static com.charts.general.utils.ParameterUtils.verifyMonthsList;
import static com.charts.general.utils.ParameterUtils.verifyYears;
import static com.charts.general.utils.ParameterUtils.verifyYearsList;

public abstract class AbstractParameters {

	private final List<String> month;
	private final List<String> year;

	protected AbstractParameters(List<String> month, List<String> year) {
		this.month = month;
		this.year = year;
	}

	public List<String> getMonth() {
		return verifyMonthsList(month);
	}

	public List<String> getYear() {
		return verifyYearsList(year);
	}

	public List<Integer> getYearInteger() {
		return verifyYears(year);
	}
}
