package com.charts.general.entity.nivo.bar;

import com.charts.general.entity.enums.Validity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NivoBarValidityDataByMonth {

	private String month;

	@JsonProperty("Mesačná")
	private Long monthly;

	@JsonProperty("3 Mesačná")
	private Long threeMonths;

	@JsonProperty("5 Mesčná")
	private Long fiveMonths;

	@JsonProperty("Ročná")
	private Long yearly;

	public NivoBarValidityDataByMonth(String month) {
		this.month = month;
	}

	public void setData(String validity, Long value) {

		if (Validity.MONTHLY.getValue().equals(validity)) {
			this.monthly = value;
		}

		if (Validity.THREE_MONTHS.getValue().equals(validity)) {
			this.threeMonths = value;
		}


		if (Validity.FIVE_MONTHS.getValue().equals(validity)) {
			this.fiveMonths = value;
		}


		if (Validity.YEARLY.getValue().equals(validity)) {
			this.yearly = value;
		}
	}

	public String getMonth() {
		return month;
	}

	public Long getMonthly() {
		return monthly;
	}

	public Long getThreeMonths() {
		return threeMonths;
	}

	public Long getFiveMonths() {
		return fiveMonths;
	}

	public Long getYearly() {
		return yearly;
	}
}
