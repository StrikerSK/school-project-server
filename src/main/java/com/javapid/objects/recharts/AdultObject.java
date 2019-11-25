package com.javapid.objects.recharts;

import com.javapid.entity.enums.PersonType;

public class AdultObject extends PersonAbstractClass {

	public AdultObject(String month, Long ticketCount) {
		super(PersonType.ADULT.value, month, ticketCount);
	}
}
