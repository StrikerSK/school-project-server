package com.javapid.entity.nivo.pie;

import com.javapid.entity.enums.PersonType;

public class NivoPiePortableData extends NivoPieAbstractData {

	public NivoPiePortableData(Long value) {
		super(PersonType.PORTABLE.getValue(), value);
	}
}
