package com.javapid.service;

import com.javapid.entity.nivo.DataSumDTO;
import com.javapid.entity.nivo.*;
import com.javapid.entity.nivo.line.*;
import com.javapid.entity.nivo.pie.*;
import com.javapid.repository.PidRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NivoDataService {

	private final PidRepository repository;

	public NivoDataService(PidRepository repository) {
		this.repository = repository;
	}

	public List<NivoLineAbstractData> getNivoLineData(){
		List<NivoLineAbstractData> personList = new ArrayList<>();
		personList.add(new NivoLineAdultData(repository.getAdultSum()));
		personList.add(new NivoLineStudentData(repository.getStudentSum()));
		personList.add(new NivoLineSeniorData(repository.getSeniorSum()));
		personList.add(new NivoLineJuniorData(repository.getJuniorSum()));
		personList.add(new NivoLinePortableData(repository.getPortableSum()));
		return personList;
	}

	public List<NivoBarData> getNivoBarData(){
		return repository.getNivoBarData();
	}

	public List<NivoPieAbstractData> getNivoPieData(){
		DataSumDTO pieData = repository.getNivoPieData();
		List<NivoPieAbstractData> outputData = new ArrayList<>();
		outputData.add(new NivoPieAdultData(pieData.getAdults()));
		outputData.add(new NivoPieStudentData(pieData.getStudents()));
		outputData.add(new NivoPieSeniorData(pieData.getSeniors()));
		outputData.add(new NivoPieJuniorData(pieData.getJuniors()));
		outputData.add(new NivoPiePortableData(pieData.getPortable()));
		return outputData;
	}
}
