package com.javapid.entity.nivo.pie;

import com.javapid.entity.enums.PersonType;

public class NivoPieSeniorData extends NivoPieAbstractData {

	public NivoPieSeniorData(Long value) {
		super(PersonType.SENIOR.getValue(), value);
	}
}
