package com.charts.apex.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApexchartsObject {

	private String name;

	@JsonProperty("data")
	private List<Long> values;

	public ApexchartsObject(String name, List<Long> values) {
		this.name = name;
		this.values = values;
	}
}
