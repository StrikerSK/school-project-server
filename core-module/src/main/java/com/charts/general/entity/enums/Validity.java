package com.charts.general.entity.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Validity {

	MONTHLY("Mesačná"),
	THREE_MONTHS("3 Mesačná"),
	FIVE_MONTHS("5 Mesačná"),
	YEARLY("Ročná");

	public String value;

	private Validity(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Validity validityValue(String label) {
		return Arrays.stream(values())
				.filter(e -> e.value.equals(label))
				.findFirst()
				.orElse(null);
	}

	public static List<String> validityValues() {
		return Arrays.stream(values())
				.map(Validity::getValue)
				.collect(Collectors.toList());
	}

}
