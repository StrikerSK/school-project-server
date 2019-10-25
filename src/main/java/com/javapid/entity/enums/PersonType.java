package com.javapid.entity.enums;

public enum PersonType implements GetterValue {

	ADULT("Dospelý", "dospeli"),
	SENIOR("Dôchodcovia", "dochodcovia"),
	JUNIOR("Juniori", "junior"),
	PORTABLE("Prenosná", "prenosna"),
	STUDENT("Študenti", "studenti");

	public final String value;
	public final String column;

	PersonType(String value, String column) {
		this.value = value;
		this.column = column;
	}

	@Override
	public String getValue() {
		return value;
	}

	public String getColumn(){
		return column;
	}
}
