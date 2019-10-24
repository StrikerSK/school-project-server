package com.javapid.entity.nivo;

public class NivoBarDataByValidity extends NivoBarDataAbstract {

	private String validity;

	public NivoBarDataByValidity(String validity, Long adults, Long seniors, Long juniors, Long students, Long portable) {
		this.validity = validity;
		setAdults(adults);
		setJuniors(juniors);
		setPortable(portable);
		setSeniors(seniors);
		setStudents(students);
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}
}
