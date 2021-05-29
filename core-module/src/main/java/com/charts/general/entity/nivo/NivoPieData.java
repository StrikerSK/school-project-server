package com.charts.general.entity.nivo;

import lombok.Data;

@Data
public class NivoPieData {

	private String id;
	private String label;
	private Long value;

	public NivoPieData(String label) {
		this.id = label;
		this.label = label;
	}

	public NivoPieData(String label, Long value) {
		this.id = label;
		this.label = label;
		this.value = value;
	}
}
