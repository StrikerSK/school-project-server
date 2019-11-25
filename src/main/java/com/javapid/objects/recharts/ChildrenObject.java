package com.javapid.objects.recharts;

import com.javapid.entity.enums.PersonType;

public class ChildrenObject extends PersonAbstractClass {

	public ChildrenObject(String month, Long value) {
		super(PersonType.CHILDREN.value, month, value);
	}
}
