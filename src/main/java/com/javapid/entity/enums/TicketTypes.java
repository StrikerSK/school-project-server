package com.javapid.entity.enums;

public enum TicketTypes implements GetterValue, GetterColumn {

	FIFTEEN_MINUTES("15 Minútové", "stvrt_minut"),
	ONE_DAY("24 Hodinový - Pásmo P", "jeden_den"),
	ONE_DAY_ALL("24 Hodinový - Všetky pásma", "jeden_den_vsetky"),
	TWO_ZONES("2 Pásma", "dve_pasma"),
	THREE_ZONES("3 Pásma", "tri_pasma"),
	FOUR_ZONES("4 Pásma", "styri_pasma"),
	FIVE_ZONES("5 Pásiem", "pat_pasem"),
	SIX_ZONES("6 Pásiem", "sest_pasem"),
	SEVEN_ZONES("7 Pásiem", "sedem_pasem"),
	EIGHT_ZONES("8 Pásiem", "osem_pasem"),
	NINE_ZONES("9 Pásiem", "devat_pasem"),
	TEN_ZONES("10 Pásiem", "desat_pasem"),
	ELEVEN_ZONES("11 Pásiem", "jedenast_pasem");

	public final String value;
	public final String column;

	private TicketTypes(String value, String column) {
		this.value = value;
		this.column = column;
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
