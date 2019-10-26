package com.javapid.service;

import com.javapid.entity.enums.GetterColumn;
import com.javapid.entity.enums.GetterValue;

import java.util.Arrays;

public abstract class ServiceAbstract {

	public  <T extends GetterValue & GetterColumn> String getColumnName(String name, T[] enumList) {
		return Arrays.stream(enumList)
				.filter(e -> name.equals(e.getValue()))
				.findAny().get().getColumn();
	}

}
