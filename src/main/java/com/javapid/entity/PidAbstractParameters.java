package com.javapid.entity;

import java.util.List;

import static com.javapid.service.Validators.*;

abstract class PidAbstractParameters {

	private List<String> month;
	private List<String> year;

	public PidAbstractParameters() { }

	PidAbstractParameters(List<String> month, List<String> year) {
		this.month = month;
		this.year = year;
	}

	public List<String> getMonth() {
		return verifyMonthsList(month);
	}

	public void setMonth(List<String> month) {
		this.month = month;
	}

	public List<String> getYear() {
		return verifyYearsList(year);
	}

	public void setYear(List<String> year) {
		this.year = year;
	}

	public List<Integer> getYearInteger() {
		return verifyYears(year);
	}
}
