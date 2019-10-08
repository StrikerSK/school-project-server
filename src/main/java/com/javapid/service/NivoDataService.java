package com.javapid.service;

import com.javapid.entity.nivo.DataSumDTO;
import com.javapid.entity.nivo.*;
import com.javapid.entity.nivo.line.*;
import com.javapid.entity.nivo.pie.*;
import com.javapid.repository.PidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NivoDataService {

	@Autowired
	private PidRepository repository;

	public List<NivoLineAbstractData> getNivoData(){
		NivoLineAbstractData adultData = new NivoLineAdultData(repository.getAdultSum());
		NivoLineAbstractData studentData = new NivoLineStudentData(repository.getStudentSum());
		NivoLineAbstractData seniorData = new NivoLineSeniorData(repository.getSeniorSum());
		NivoLineAbstractData juniorData  = new NivoLineJuniorData(repository.getJuniorSum());
		NivoLineAbstractData portableData  = new NivoLinePortableData(repository.getPortableSum());

		List<NivoLineAbstractData> personList = new ArrayList<>();
		personList.add(adultData);
		personList.add(studentData);
		personList.add(seniorData);
		personList.add(juniorData);
		personList.add(portableData);

		return personList;
	}

	public List<NivoBarData> getNivoBarData(){
		return repository.getNivoBarData();
	}

	public List<NivoPieAbstractData> getNivoPieData(){
		DataSumDTO pieData = repository.getNivoPieData();
		List<NivoPieAbstractData> outputData = new ArrayList<>();

		NivoPieAbstractData adultData = new NivoPieAdultData(pieData.getAdults());
		NivoPieAbstractData studentData = new NivoPieStudentData(pieData.getStudents());
		NivoPieAbstractData seniorData = new NivoPieSeniorData(pieData.getSeniors());
		NivoPieAbstractData juniorData  = new NivoPieJuniorData(pieData.getJuniors());
		NivoPieAbstractData portableData  = new NivoPiePortableData(pieData.getPortable());

		outputData.add(adultData);
		outputData.add(studentData);
		outputData.add(seniorData);
		outputData.add(juniorData);
		outputData.add(portableData);

		return outputData;
	}
}
