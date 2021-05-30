package com.charts.general;

import lombok.SneakyThrows;

public class ClassMethodInvoker {

	@SneakyThrows
	public static Object invokeClassGetMethod(Object inputType, String fieldName) {
		return inputType.getClass().getMethod("get" + fieldName).invoke(inputType);
	}

}
