package com.javapid.entity.nivo.pie;

import com.javapid.entity.enums.PersonType;

public class NivoPieJuniorData extends NivoPieAbstractData {

	public NivoPieJuniorData(Long value) {
		super(PersonType.JUNIOR.getValue(), value);
	}
}
