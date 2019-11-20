package com.javapid.entity.nivo.bar;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class NivoBarDataAbstract {

	@JsonProperty("Dospelý")
	private Long adults = 0L;

	@JsonProperty("Dôchodcovia")
	private Long seniors = 0L;

	@JsonProperty("Juniori")
	private Long juniors = 0L;

	@JsonProperty("Študenti")
	private Long students = 0L;

	@JsonProperty("Prenosné")
	private Long portable = 0L;

	@JsonProperty("Deti")
	private Long children = 0L;

	public NivoBarDataAbstract() {
	}

	public NivoBarDataAbstract(Long adults, Long seniors, Long juniors, Long students, Long portable, Long children) {
		this.adults = adults;
		this.seniors = seniors;
		this.juniors = juniors;
		this.students = students;
		this.portable = portable;
		this.children = children;
	}

	public Long getAdults() {
		return adults;
	}

	public void setAdults(Long adults) {
		this.adults = adults;
	}

	public void addToAdults(Long adults) {
		this.adults += adults;
	}

	public Long getSeniors() {
		return seniors;
	}

	public void setSeniors(Long seniors) {
		this.seniors = seniors;
	}

	public void addToSeniors(Long seniors) {
		this.seniors += seniors;
	}

	public Long getJuniors() {
		return juniors;
	}

	public void setJuniors(Long juniors) {
		this.juniors = juniors;
	}

	public void addToJuniors(Long juniors) {
		this.juniors += juniors;
	}

	public Long getStudents() {
		return students;
	}

	public void setStudents(Long students) {
		this.students = students;
	}

	public void addToStudents(Long students) {
		this.students += students;
	}

	public Long getPortable() {
		return portable;
	}

	public void setPortable(Long portable) {
		this.portable = portable;
	}

	public void addToPortable(Long portable) {
		this.portable += portable;
	}

	public Long getChildren() {
		return children;
	}

	public void setChildren(Long children) {
		this.children = children;
	}
}
