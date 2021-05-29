package com.charts.general.entity.nivo;

import lombok.Data;

@Data
public class DataXY {

	private String x;
	private Long y;

	public DataXY() {
	}

	public DataXY(String x, Long y) {
		this.x = x;
		this.y = y;
	}
}
