package com.javapid.entity.nivo.bar;

public class NivoBarCouponDataByValidity extends NivoBarCouponData {

	private String validity;

	public NivoBarCouponDataByValidity(String validity) {
		this.validity = validity;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}
}
