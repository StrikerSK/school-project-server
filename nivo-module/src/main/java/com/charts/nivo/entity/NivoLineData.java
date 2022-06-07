package com.charts.nivo.entity;

import lombok.Data;

import java.util.List;

@Data
public class NivoLineData {

	private String id;
	private List<NivoDataXY> data;

	public NivoLineData(String id, List<NivoDataXY> data) {
		this.id = id;
		this.data = data;
	}
}
