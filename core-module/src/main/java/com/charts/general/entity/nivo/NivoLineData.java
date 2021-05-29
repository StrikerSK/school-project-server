package com.charts.general.entity.nivo;

import lombok.Data;

import java.util.List;

@Data
public class NivoLineData {

	private String id;
	private List<DataXY> data;

	NivoLineData() {
	}

	public NivoLineData(String id) {
		this.id = id;
	}

	public NivoLineData(String id, List<DataXY> data) {
		this.id = id;
		this.data = data;
	}
}
