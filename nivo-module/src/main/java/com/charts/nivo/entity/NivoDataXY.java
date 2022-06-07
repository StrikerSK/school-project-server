package com.charts.nivo.entity;

import lombok.Data;

@Data
public class NivoDataXY {

	private String x;
	private Long y;

	public NivoDataXY() {
	}

	public NivoDataXY(String x, Long y) {
		this.x = x;
		this.y = y;
	}
}
