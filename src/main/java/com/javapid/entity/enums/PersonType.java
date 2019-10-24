package com.javapid.entity.enums;

public enum PersonType implements ValueGetter {

	ADULT("Dospelý", "dospeli"),
	SENIOR("Dôchodcovia", "dochodcovia"),
	JUNIOR("Juniori", "junior"),
	PORTABLE("Prenosná", "prenosna"),
	STUDENT("Študenti", "studenti");

	private String value;
	private String column;

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
