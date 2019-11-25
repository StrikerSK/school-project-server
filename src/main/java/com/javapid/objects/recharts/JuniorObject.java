package com.javapid.objects.recharts;

import com.javapid.entity.enums.PersonType;

public class JuniorObject extends PersonAbstractClass {

	public JuniorObject(String month, Long ticketCount) {
		super(PersonType.JUNIOR.value, month, ticketCount);
	}
}
