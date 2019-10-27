package com.javapid.entity.nivo.bar;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class NivoBarDataAbstract {

	@JsonProperty("Dospelý")
	private Long adults;

	@JsonProperty("Dôchodcovia")
	private Long seniors;

	@JsonProperty("Juniori")
	private Long juniors;

	@JsonProperty("Študenti")
	private Long students;

	@JsonProperty("Prenosné")
	private Long portable;

	NivoBarDataAbstract(Long adults, Long seniors, Long juniors, Long students, Long portable) {
		this.adults = adults;
		this.seniors = seniors;
		this.juniors = juniors;
		this.students = students;
		this.portable = portable;
	}

	public Long getAdults() {
		return adults;
	}

	public void setAdults(Long adults) {
		this.adults = adults;
	}

	public Long getSeniors() {
		return seniors;
	}

	public void setSeniors(Long seniors) {
		this.seniors = seniors;
	}

	public Long getJuniors() {
		return juniors;
	}

	public void setJuniors(Long juniors) {
		this.juniors = juniors;
	}

	public Long getStudents() {
		return students;
	}

	public void setStudents(Long students) {
		this.students = students;
	}

	public Long getPortable() {
		return portable;
	}

	public void setPortable(Long portable) {
		this.portable = portable;
	}
}
