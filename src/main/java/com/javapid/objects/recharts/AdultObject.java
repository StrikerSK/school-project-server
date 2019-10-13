package com.javapid.objects.recharts;

public class AdultObject extends PersonAbstractClass {

	public AdultObject(String month, Long ticketCount) {
		setName("Dospelý");
		setMonth(month);
		setValue(ticketCount);
	}
}
