package com.javapid.entity.enums;

public enum PersonType implements ValueGetter {

	ADULT("Dospelý"),
	SENIOR("Dôchodcovia"),
	JUNIOR("Juniori"),
	PORTABLE("Prenosná"),
	STUDENT("Študenti");

	private String value;

	private PersonType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
