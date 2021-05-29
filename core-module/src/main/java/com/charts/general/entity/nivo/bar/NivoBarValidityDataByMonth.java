package com.charts.general.entity.nivo.bar;

import com.charts.general.entity.enums.Validity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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

		switch (Validity.validityValue(validity)) {
			case MONTHLY:
				setMonthly(value);
				break;
			case THREE_MONTHS:
				setThreeMonths(value);
				break;
			case FIVE_MONTHS:
				setFiveMonths(value);
				break;
			case YEARLY:
				setYearly(value);
				break;
			default:
				break;
		}
	}
}
