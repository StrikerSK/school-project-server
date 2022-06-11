package com.charts.apex.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApexObject {

	private String name;

	@JsonProperty("data")
	private List<Integer> values;

	public ApexObject(String name) {
		this.name = name;
	}

	public ApexObject(String name, List<Integer> values) {
		this.name = name;
		this.values = values;
	}

	public ApexObject withList(List<Integer> values) {
		this.values = values;
		return this;
	}

}
