package com.javapid.objects.recharts;

public class AreaChartData {

	private String name;
	private Integer adults;
	private Integer students;
	private Integer portable;
	private Integer seniors;
	private Integer juniors;

	public AreaChartData() {
	}

	public AreaChartData(String name, Integer adults, Integer students, Integer portable, Integer seniors, Integer juniors) {
		this.name = name;
		this.adults = adults;
		this.students = students;
		this.portable = portable;
		this.seniors = seniors;
		this.juniors = juniors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAdults() {
		return adults;
	}

	public void setAdults(Integer adults) {
		this.adults = adults;
	}

	public Integer getStudents() {
		return students;
	}

	public void setStudents(Integer students) {
		this.students = students;
	}

	public Integer getPortable() {
		return portable;
	}

	public void setPortable(Integer portable) {
		this.portable = portable;
	}

	public Integer getSeniors() {
		return seniors;
	}

	public void setSeniors(Integer seniors) {
		this.seniors = seniors;
	}

	public Integer getJuniors() {
		return juniors;
	}

	public void setJuniors(Integer juniors) {
		this.juniors = juniors;
	}
}
