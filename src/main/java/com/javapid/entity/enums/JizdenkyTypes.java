package com.javapid.entity.enums;

public enum JizdenkyTypes implements ValueGetter{

	FIFTEEN_MINUTES("15 Minútové"),
	ONE_DAY("24 Hodinový - Pásmo P"),
	ONE_DAY_ALL("24 Hodinový - Všetky pásma"),
	TWO_ZONES("2 Pásma"),
	THREE_ZONES("3 Pásma"),
	FOUR_ZONES("4 Pásma"),
	FIVE_ZONES("5 Pásiem"),
	SIX_ZONES("6 Pásiem"),
	SEVEN_ZONES("7 Pásiem"),
	EIGHT_ZONES("8 Pásiem"),
	NINE_ZONES("9 Pásiem"),
	TEN_ZONES("10 Pásiem"),
	ELEVEN_ZONES("11 Pásiem");

	private String value;

	private JizdenkyTypes(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

}
