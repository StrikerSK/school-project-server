package com.charts.general.entity.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum YearOptions {

	TWO_OH_SIXTEEN("2016"),
	TWO_OH_SEVENTEEN("2017"),
	TWO_OH_EIGHTEEN("2018"),
	TWO_OH_NINETEEN("2019");

	public String value;

	private YearOptions(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static List<String> yearValues() {
		return Arrays.stream(values())
				.map(YearOptions::getValue)
				.collect(Collectors.toList());
	}

}
