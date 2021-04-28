package com.charts.general.entity.nivo;

public class NivoPieData {

	private String id;
	private String label;
	private Long value;

	public NivoPieData(String label) {
		this.id = label;
		this.label = label;
	}

	public NivoPieData(String label, Long value) {
		this.id = label;
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

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
