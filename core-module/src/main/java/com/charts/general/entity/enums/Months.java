package com.charts.general.entity.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Months {

	JANUARY("Január"),
	FEBRUARY("Február"),
	MARCH("Marec"),
	APRIL("Apríl"),
	MAY("Máj"),
	JUNE("Jún"),
	JULY("Júl"),
	AUGUST("August"),
	SEPTEMBER("September"),
	OCTOBER("Október"),
	NOVEMBER("November"),
	DECEMBER("December");

	public final String value;

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
