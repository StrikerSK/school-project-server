package com.charts.general;

import com.charts.general.entity.nivo.DataXY;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassMethodInvokerTest {

	private final DataXY data = new DataXY("CustomName", 555L);

	@Test
	public void invokeGetMethodOnLong(){
		Long value = (Long) ClassMethodInvoker.invokeClassGetMethod(data, "Y");
		Assertions.assertEquals(new Long(555), value);
	}

	@Test
	public void invokeGetMethodOnString(){
		String value = (String) ClassMethodInvoker.invokeClassGetMethod(data, "X");
		Assertions.assertEquals("CustomName", value);
	}

}
