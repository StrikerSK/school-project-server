package com.charts.general.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Optional;

public enum Validity implements IEnum {

	@JsonProperty("Mesačná")
	MONTHLY("Mesačná"),

	@JsonProperty("3 Mesačná")
	THREE_MONTHS("3 Mesačná"),

	@JsonProperty("5 Mesačná")
	FIVE_MONTHS("5 Mesačná"),

	@JsonProperty("Ročná")
	YEARLY("Ročná");

	private final String value;

	Validity(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Optional<Validity> validityValue(String label) {
		return Arrays.stream(Validity.values())
				.filter(c -> c.getValue().equals(label))
				.findFirst();
	}

}
