package com.javapid.object;

public class TestObject {

	private String id;
	private String label;
	private Integer value;

	public TestObject(String id, Integer value) {
		this.id = id;
		this.label = id;
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
