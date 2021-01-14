package com.javapid.entity.nivo.pie;

import com.javapid.entity.enums.PersonType;

public class NivoPieStudentData extends NivoPieAbstractData {

	public NivoPieStudentData(Long value) {
		super(PersonType.STUDENT.getValue(), value);
	}
}
