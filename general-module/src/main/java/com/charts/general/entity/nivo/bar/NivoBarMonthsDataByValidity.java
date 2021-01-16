package com.charts.general.entity.nivo.bar;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.charts.general.entity.enums.Months;

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

	public String getValidity() {
		return validity;
	}

	public Long getJanuary() {
		return january;
	}

	public Long getFebruary() {
		return february;
	}

	public Long getMarch() {
		return march;
	}

	public Long getApril() {
		return april;
	}

	public Long getMay() {
		return may;
	}

	public Long getJune() {
		return june;
	}

	public Long getJuly() {
		return july;
	}

	public Long getAugust() {
		return august;
	}

	public Long getSeptember() {
		return september;
	}

	public Long getOctober() {
		return october;
	}

	public Long getNovember() {
		return november;
	}

	public Long getDecember() {
		return december;
	}

	public void setData(String month, Long value){

		if(Months.JANUARY.getValue().equals(month)) {
			this.january = value;
		}

		if(Months.FEBRUARY.getValue().equals(month)) {
			this.february = value;
		}

		if(Months.MARCH.getValue().equals(month)) {
			this.march = value;
		}

		if(Months.APRIL.getValue().equals(month)) {
			this.april = value;
		}

		if(Months.MAY.getValue().equals(month)) {
			this.may = value;
		}

		if(Months.JUNE.getValue().equals(month)) {
			this.june = value;
		}

		if(Months.JULY.getValue().equals(month)) {
			this.july = value;
		}

		if(Months.AUGUST.getValue().equals(month)) {
			this.august = value;
		}

		if(Months.SEPTEMBER.getValue().equals(month)) {
			this.september = value;
		}

		if(Months.OCTOBER.getValue().equals(month)) {
			this.october = value;
		}

		if(Months.NOVEMBER.getValue().equals(month)) {
			this.november = value;
		}

		if(Months.DECEMBER.getValue().equals(month)) {
			this.december = value;
		}
	};
}
