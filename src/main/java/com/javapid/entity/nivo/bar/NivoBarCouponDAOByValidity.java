package com.javapid.entity.nivo.bar;

public class NivoBarCouponDAOByValidity extends NivoBarCouponDAO {

	private String validity;

	public NivoBarCouponDAOByValidity(String validity) {
		this.validity = validity;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}
}
