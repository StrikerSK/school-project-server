package com.charts.general.entity.nivo.bar;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;

import static com.charts.general.constants.Months.*;
import static com.charts.general.entity.enums.Months.monthValue;

@Data
public class NivoBarMonthsDataByValidity {

	private String validity;

	@JsonProperty(JANUARY_VALUE)
	private Long january = 0L;

	@JsonProperty(FEBRUARY_VALUE)
	private Long february = 0L;

	@JsonProperty(MARCH_VALUE)
	private Long march = 0L;

	@JsonProperty(APRIL_VALUE)
	private Long april = 0L;

	@JsonProperty(MAY_VALUE)
	private Long may = 0L;

	@JsonProperty(JUNE_VALUE)
	private Long june = 0L;

	@JsonProperty(JULY_VALUE)
	private Long july = 0L;

	@JsonProperty(AUGUST_VALUE)
	private Long august = 0L;

	@JsonProperty(SEPTEMBER_VALUE)
	private Long september = 0L;

	@JsonProperty(OCTOBER_VALUE)
	private Long october = 0L;

	@JsonProperty(NOVEMBER_VALUE)
	private Long november = 0L;

	@JsonProperty(DECEMBER_VALUE)
	private Long december = 0L;

	public NivoBarMonthsDataByValidity(String validity) {
		this.validity = validity;
	}

	public void setData(String month, Long value){

		switch (Objects.requireNonNull(monthValue(month))) {
			case JANUARY:
				setJanuary(value);
				break;
			case FEBRUARY:
				setFebruary(value);
				break;
			case MARCH:
				setMarch(value);
				break;
			case APRIL:
				setApril(value);
				break;
			case MAY:
				setMay(value);
				break;
			case JUNE:
				setJune(value);
				break;
			case JULY:
				setJuly(value);
				break;
			case AUGUST:
				setAugust(value);
				break;
			case SEPTEMBER:
				setSeptember(value);
				break;
			case OCTOBER:
				setOctober(value);
				break;
			case NOVEMBER:
				setNovember(value);
				break;
			case DECEMBER:
				setDecember(value);
				break;
			default:
				break;
		}
	};
}
