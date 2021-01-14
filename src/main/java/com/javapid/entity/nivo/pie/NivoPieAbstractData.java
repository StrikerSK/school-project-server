package com.javapid.entity.nivo.pie;

public abstract class NivoPieAbstractData {

	private String id;
	private String label;
	private Long value;

	public NivoPieAbstractData(String name) {
		setId(name);
		setLabel(name);
	}

	public NivoPieAbstractData(String name, Long value) {
		setId(name);
		setLabel(name);
		setValue(value);
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
