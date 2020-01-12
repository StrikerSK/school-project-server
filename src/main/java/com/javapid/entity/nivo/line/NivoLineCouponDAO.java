package com.javapid.entity.nivo.line;

import com.javapid.entity.nivo.DataXY;

import java.util.List;

public class NivoLineCouponDAO {

	private String id;
	private List<DataXY> data;

	NivoLineCouponDAO() {
	}

	public NivoLineCouponDAO(String id) {
		this.id = id;
	}

	public NivoLineCouponDAO(String id, List<DataXY> data) {
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
