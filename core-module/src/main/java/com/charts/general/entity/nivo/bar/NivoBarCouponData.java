package com.charts.general.entity.nivo.bar;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.charts.general.constants.PersonType.*;

public class NivoBarCouponData {

	@JsonProperty(ADULT_VALUE)
	private Long adults = 0L;

	@JsonProperty(SENIOR_VALUE)
	private Long seniors = 0L;

	@JsonProperty(JUNIOR_VALUE)
	private Long juniors = 0L;

	@JsonProperty(STUDENT_VALUE)
	private Long students = 0L;

	@JsonProperty(PORTABLE_VALUE)
	private Long portable = 0L;

	@JsonProperty(CHILDREN_VALUE)
	private Long children = 0L;

	public NivoBarCouponData() {
	}

	public NivoBarCouponData(Long adults, Long seniors, Long juniors, Long students, Long portable, Long children) {
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

	public void addToChildren(Long children) {
		this.children += children;
	}
}
