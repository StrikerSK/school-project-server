package com.charts.general.entity.nivo.bar;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static com.charts.general.entity.enums.Months.monthValue;

@Data
@Slf4j
public class NivoBarMonthsDataByValidity {

	private String validity;

	@JsonProperty("Január")
	private Long january = 0L;

	@JsonProperty("Február")
	private Long february = 0L;

	@JsonProperty("Marec")
	private Long march = 0L;

	@JsonProperty("Apríl")
	private Long april = 0L;

	@JsonProperty("Máj")
	private Long may = 0L;

	@JsonProperty("Jún")
	private Long june = 0L;

	@JsonProperty("Júl")
	private Long july = 0L;

	@JsonProperty("August")
	private Long august = 0L;

	@JsonProperty("September")
	private Long september = 0L;

	@JsonProperty("Október")
	private Long october = 0L;

	@JsonProperty("November")
	private Long november = 0L;

	@JsonProperty("December")
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
				log.info("Value cannot be assigned to any month!");
				break;
		}
	};
}
