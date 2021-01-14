package com.javapid.entity.nivo.pie;

import com.javapid.entity.enums.PersonType;

public class NivoPieAdultData extends NivoPieAbstractData {

	public NivoPieAdultData(Long value) {
		super(PersonType.ADULT.getValue(), value);
	}
}
