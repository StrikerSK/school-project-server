package com.charts.general.entity.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.charts.general.constants.Months.*;

public enum Months {

	JANUARY(JANUARY_VALUE),
	FEBRUARY(FEBRUARY_VALUE),
	MARCH(MARCH_VALUE),
	APRIL(APRIL_VALUE),
	MAY(MAY_VALUE),
	JUNE(JUNE_VALUE),
	JULY(JULY_VALUE),
	AUGUST(AUGUST_VALUE),
	SEPTEMBER(SEPTEMBER_VALUE),
	OCTOBER(OCTOBER_VALUE),
	NOVEMBER(NOVEMBER_VALUE),
	DECEMBER(DECEMBER_VALUE);

	private final String value;

	private Months(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Months monthValue(String label) {
		return Arrays.stream(values())
				.filter(e -> e.value.equals(label))
				.findFirst()
				.orElse(null);
	}

	public static List<String> monthsValues() {
		return Arrays.stream(values())
				.map(Months::getValue)
				.collect(Collectors.toList());
	}


}
