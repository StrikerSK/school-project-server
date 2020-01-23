package com.javapid.entity.nivo;

import java.util.List;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DataXY> getData() {
		return data;
	}

	public void setData(List<DataXY> data) {
		this.data = data;
	}
}
