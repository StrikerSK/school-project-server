package com.charts.general.entity.enums;

public enum PersonType implements GetterValue, GetterColumn {

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

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getColumn() {
		return column;
	}
}
