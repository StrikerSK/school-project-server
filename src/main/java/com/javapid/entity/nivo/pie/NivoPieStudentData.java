package com.javapid.entity.nivo.pie;

import com.javapid.entity.enums.PersonType;

public class NivoPieStudentData extends NivoPieAbstractData {

	public NivoPieStudentData(Long value) {
		setId(PersonType.STUDENT.getValue());
		setLabel(PersonType.STUDENT.getValue());
		setValue(value);
	}
}
