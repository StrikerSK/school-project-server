package com.charts.general.entity;

import java.util.List;

import static com.charts.general.service.Validators.verifyMonthsList;
import static com.charts.general.service.Validators.verifyYears;
import static com.charts.general.service.Validators.verifyYearsList;

abstract class PidAbstractParameters {

	private final List<String> month;
	private final List<String> year;

	PidAbstractParameters(List<String> month, List<String> year) {
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
