package com.javapid.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApexchartsData {

	private String name;

	@JsonProperty("data")
	private List<Long> values;

	public ApexchartsData() {
	}

	public ApexchartsData(String name, List<Long> values) {
		this.name = name;
		this.values = values;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getValues() {
		return values;
	}

	public void setValues(List<Long> values) {
		this.values = values;
	}
}
