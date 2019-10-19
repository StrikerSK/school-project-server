package com.javapid.entity.enums;

public enum SellType implements ValueGetter {

	CARD("Čipová karta"),
	COUPON("Papierový kupón");

	private String value;

	private SellType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
