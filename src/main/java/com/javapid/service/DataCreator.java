package com.javapid.service;

import com.javapid.entity.PidData;
import com.javapid.objects.TicketSellData;
import com.javapid.objects.recharts.AreaChartData;

public class DataCreator {

	public static TicketSellData createStreamData(PidData data){
		TicketSellData sellData = new TicketSellData();
		sellData.setAdults(data.getAdults());
		sellData.setJuniors(data.getJunior());
		sellData.setPortable(data.getPortable());
		sellData.setSeniors(data.getSeniors());
		sellData.setStudents(data.getStudents());
		return sellData;
	}

	public static AreaChartData createAreaChartData(PidData data){
		AreaChartData sellData = new AreaChartData();
		sellData.setName(data.getMonth());
		sellData.setAdults(data.getAdults());
		sellData.setJuniors(data.getJunior());
		sellData.setPortable(data.getPortable());
		sellData.setSeniors(data.getSeniors());
		sellData.setStudents(data.getStudents());
		return sellData;
	}

}
