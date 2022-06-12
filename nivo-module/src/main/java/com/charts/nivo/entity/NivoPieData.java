package com.charts.nivo.entity;

import com.charts.general.entity.enums.IEnum;
import lombok.Data;

@Data
public class NivoPieData {

	private String id;
	private String label;
	private Integer value;

	public NivoPieData(String label, Integer value) {
		this.id = label;
		this.label = label;
		this.value = value;
	}

	public NivoPieData(IEnum label, Integer value) {
		this.id = label.getValue();
		this.label = label.getValue();
		this.value = value;
	}
}
