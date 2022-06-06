package com.charts.apex.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApexObject {

	private String name;

	@JsonProperty("data")
	private List<Long> values;

	public ApexObject(String name) {
		this.name = name;
	}

	public ApexObject(String name, List<Long> values) {
		this.name = name;
		this.values = values;
	}

	public ApexObject withList(List<Long> values) {
		this.values = values;
		return this;
	}

}
