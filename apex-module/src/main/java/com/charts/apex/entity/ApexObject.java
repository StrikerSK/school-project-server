package com.charts.apex.entity;

import com.charts.general.entity.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApexObject {

	@JsonIgnore
	private Integer orderValue;

	private String name;

	@JsonProperty("data")
	private List<Integer> values;

	public ApexObject(String name) {
		this.name = name;
	}

	public ApexObject(IEnum name) {
		this.name = name.getValue();
	}

	public ApexObject(IEnum name, List<Integer> values) {
		this.name = name.getValue();
		this.orderValue = name.getOrderValue();
		this.values = values;
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
