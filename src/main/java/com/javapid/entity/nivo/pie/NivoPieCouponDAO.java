package com.javapid.entity.nivo.pie;

public class NivoPieCouponDAO {

	private String id;
	private String label;
	private Long value;

	public NivoPieCouponDAO(String label) {
		this.id = label;
		this.label = label;
	}

	public NivoPieCouponDAO(String label, Long value) {
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
