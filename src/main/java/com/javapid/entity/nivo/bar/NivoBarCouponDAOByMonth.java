package com.javapid.entity.nivo.bar;

public class NivoBarCouponDAOByMonth extends NivoBarCouponDAO {

	private String month;

	public NivoBarCouponDAOByMonth(String month, Long adults, Long seniors, Long juniors, Long students, Long portable, Long children) {
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
