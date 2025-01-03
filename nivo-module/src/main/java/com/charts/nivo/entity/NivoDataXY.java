package com.charts.nivo.entity;

import com.charts.general.entity.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class NivoDataXY {

	@JsonIgnore
	private Integer orderValue;
	private String x;
	private Long y;

	public NivoDataXY() {
	}

	public NivoDataXY(String x, Long y) {
		this.x = x;
		this.y = y;
	}

	public NivoDataXY(IEnum x, Long y) {
		this.orderValue = x.getOrderValue();
		this.x = x.getValue();
		this.y = y;
	}
}
