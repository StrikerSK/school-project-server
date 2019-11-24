package com.javapid.entity.nivo.bar;

public class NivoBarDataByMonth extends NivoBarDataAbstract {

	private String month;

	public NivoBarDataByMonth(String month, Long adults, Long seniors, Long juniors, Long students, Long portable, Long children) {
		super(adults, seniors, juniors, students, portable, children);
		this.month = month;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
