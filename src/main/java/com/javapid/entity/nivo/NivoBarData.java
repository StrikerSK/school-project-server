package com.javapid.entity.nivo;

public class NivoBarData extends DataAbstract {

	private String month;

	public NivoBarData() {
	}

	public NivoBarData(String month, Long adults, Long seniors, Long juniors, Long students, Long portable) {
		this.month = month;
		setAdults(adults);
		setJuniors(juniors);
		setPortable(portable);
		setSeniors(seniors);
		setStudents(students);
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
