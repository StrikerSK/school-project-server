package com.javapid.service;

import com.javapid.entity.PidData;
import com.javapid.objects.recharts.*;

import java.util.ArrayList;
import java.util.List;

public class DataCreator {

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

	public static List<PersonAbstractClass> createPeronList(PidData data){
		List<PersonAbstractClass> personsList = new ArrayList<>();
		String month = data.getMonth();
		personsList.add(new AdultObject(month,data.getAdults()));
		personsList.add(new JuniorObject(month,data.getJunior()));
		personsList.add(new SeniorObject(month,data.getSeniors()));
		personsList.add(new PortableObject(month,data.getPortable()));
		personsList.add(new StudentObject(month,data.getStudents()));
		return personsList;
	}
}
