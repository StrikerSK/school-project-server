package com.javapid.entity.nivo;

public class PieChartData {

	private String id;
	private String label;
	private int value;

	public PieChartData() {
	}

	public PieChartData(String id, String label, int value) {
		this.id = id;
		this.label = label;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
