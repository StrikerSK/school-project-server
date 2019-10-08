package com.javapid.service;

import com.javapid.entity.nivo.*;
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
}
