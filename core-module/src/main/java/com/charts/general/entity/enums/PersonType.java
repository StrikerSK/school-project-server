package com.charts.general.entity.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PersonType {

	ADULT("Dospelý", "dospeli", "Adults"),
	SENIOR("Dôchodcovia", "dochodcovia", "Seniors"),
	JUNIOR("Juniori", "junior", "Juniors"),
	PORTABLE("Prenosná", "prenosna", "Portable"),
	STUDENT("Študenti", "studenti", "Students"),
	CHILDREN("Deti", "dieta", "Children");

	public final String value;
	public final String column;
	public final String methodValue;

	PersonType(String value, String column, String methodValue) {
		this.value = value;
		this.column = column;
		this.methodValue = methodValue;
	}

	public String getValue() {
		return value;
	}

	public String getColumn() {
		return column;
	}

	public static List<String> personValues() {
		return Arrays.stream(values())
				.map(PersonType::getValue)
				.collect(Collectors.toList());
	}

	public static PersonType personTypeValue(String label) {
		return Arrays.stream(values())
				.filter(e -> e.value.equals(label))
				.findFirst()
				.orElse(null);
	}

	public static String getPersonColumn(String value) {
		return personTypeValue(value).getColumn();
	}
}
