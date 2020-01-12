package com.javapid.entity.nivo.pie;

public class NivoPieAbstractData {

	private String id;
	private String label;
	private Long value;

	public NivoPieAbstractData(String label) {
		this.id = label;
		this.label = label;
	}

	public NivoPieAbstractData(String label, Long value) {
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
