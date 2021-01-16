package com.charts.general.entity.nivo.bar;

public class NivoBarCouponDataByMonth extends NivoBarCouponData {

	private String month;

	public NivoBarCouponDataByMonth(String month, Long adults, Long seniors, Long juniors, Long students, Long portable, Long children) {
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
