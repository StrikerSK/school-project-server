package com.charts.general.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Validity {

	@JsonProperty("Mesačná")
	MONTHLY("Mesačná"),

	@JsonProperty("3 Mesačná")
	THREE_MONTHS("3 Mesačná"),

	@JsonProperty("5 Mesačná")
	FIVE_MONTHS("5 Mesačná"),

	@JsonProperty("Ročná")
	YEARLY("Ročná");

	private final String value;

	private Validity(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Optional<Validity> validityValue(String label) {
		return Stream.of(Validity.values())
				.filter(c -> c.getValue().equals(label))
				.findFirst();
	}

	public static List<String> validityValues() {
		return Arrays.stream(values())
				.map(Validity::getValue)
				.collect(Collectors.toList());
	}

}
