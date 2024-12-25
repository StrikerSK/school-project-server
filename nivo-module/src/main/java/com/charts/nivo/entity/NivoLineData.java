package com.charts.nivo.entity;

import com.charts.general.entity.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class NivoLineData {

	@JsonIgnore
	private Integer orderValue;
	private String id;
	private List<NivoDataXY> data;

	public NivoLineData(String id, List<NivoDataXY> data) {
		this.id = id;
		this.data = data;
	}

	public NivoLineData(IEnum id, List<NivoDataXY> data) {
		this.id = id.getValue();
		this.orderValue = id.getOrderValue();
		this.data = data;
	}
}
