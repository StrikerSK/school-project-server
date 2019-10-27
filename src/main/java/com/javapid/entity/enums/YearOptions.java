package com.javapid.entity.enums;

public enum YearOptions implements GetterValue {

	TWO_OH_SIXTEEN("2016"),
	TWO_OH_SEVENTEEN("2017"),
	TWO_OH_EIGHTEEN("2018"),
	TWO_OH_NINETEEN("2019");

	private String value;

	private YearOptions(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

}
