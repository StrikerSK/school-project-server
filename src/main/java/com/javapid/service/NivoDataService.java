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

	public List<NivoAbstractLineData> getNivoData(){
		NivoAbstractLineData adultData = new NivoAdultLineData(repository.getAdultSum());
		NivoAbstractLineData studentData = new NivoStudentLineData(repository.getStudentSum());
		NivoAbstractLineData seniorData = new NivoSeniorLineData(repository.getSeniorSum());
		NivoAbstractLineData juniorData  = new NivoJuniorLineData(repository.getJuniorSum());
		NivoAbstractLineData portableData  = new NivoPortableLineData(repository.getPortableSum());

		List<NivoAbstractLineData> personList = new ArrayList<>();
		personList.add(adultData);
		personList.add(studentData);
		personList.add(seniorData);
		personList.add(juniorData);
		personList.add(portableData);

		return personList;
	}
}
