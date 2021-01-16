package com.charts.general.entity.enums;

public enum SellType implements GetterValue {

	CARD("Čipová karta"),
	COUPON("Papierový kupón"),
	ESHOP("EShop");

	private String value;

	private SellType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
