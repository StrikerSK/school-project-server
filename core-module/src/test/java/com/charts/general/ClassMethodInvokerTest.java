package com.charts.general;

import com.charts.general.entity.nivo.DataXY;
import org.junit.Assert;
import org.junit.Test;

public class ClassMethodInvokerTest {

	private final DataXY data = new DataXY("CustomName", 555L);

	@Test
	public void invokeGetMethodOnLong(){
		Long value = (Long) ClassMethodInvoker.invokeClassGetMethod(data, "Y");
		Assert.assertEquals(new Long(555), value);
	}

	@Test
	public void invokeGetMethodOnString(){
		String value = (String) ClassMethodInvoker.invokeClassGetMethod(data, "X");
		Assert.assertEquals("CustomName", value);
	}

}
