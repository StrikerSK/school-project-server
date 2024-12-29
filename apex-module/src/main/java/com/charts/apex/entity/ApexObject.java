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

	public ApexObject(IEnum name, List<Integer> values) {
		this.name = name.getValue();
		this.orderValue = name.getOrderValue();
		this.values = values;
	}

}
