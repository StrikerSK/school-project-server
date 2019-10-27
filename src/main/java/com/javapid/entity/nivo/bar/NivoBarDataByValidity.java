package com.javapid.entity.nivo.bar;

public class NivoBarDataByValidity extends NivoBarDataAbstract {

	private String validity;

	public NivoBarDataByValidity(String validity, Long adults, Long seniors, Long juniors, Long students, Long portable) {
		super(adults, seniors, juniors, students, portable);
		this.validity = validity;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}
}
