package com.charts.nivo.entity;

import com.charts.general.entity.nivo.DataXY;
import lombok.Data;

import java.util.List;

@Data
public class NivoLineData {

	private String id;
	private List<DataXY> data;

	public NivoLineData(String id, List<DataXY> data) {
		this.id = id;
		this.data = data;
	}
}
