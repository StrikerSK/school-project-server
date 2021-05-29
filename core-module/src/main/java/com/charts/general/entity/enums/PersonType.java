package com.charts.general.entity.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.charts.general.constants.PersonType.*;

public enum PersonType {

	ADULT(ADULT_VALUE, "dospeli", "Adults"),
	SENIOR(SENIOR_VALUE, "dochodcovia", "Seniors"),
	JUNIOR(JUNIOR_VALUE, "junior", "Juniors"),
	PORTABLE(PORTABLE_VALUE, "prenosna", "Portable"),
	STUDENT(STUDENT_VALUE, "studenti", "Students"),
	CHILDREN(CHILDREN_VALUE, "dieta", "Children");

	private final String value;
	private final String column;
	private final String methodValue;

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

	public String getMethodValue() {
		return methodValue;
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

	public static List<PersonType> getList() {
		return Arrays.asList(PersonType.values());
	}
}
