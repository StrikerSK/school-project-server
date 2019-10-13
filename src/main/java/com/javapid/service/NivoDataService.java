package com.javapid.service;

import com.javapid.entity.enums.Validity;
import com.javapid.entity.nivo.DataSumDTO;
import com.javapid.entity.nivo.*;
import com.javapid.entity.nivo.line.*;
import com.javapid.entity.nivo.pie.*;
import com.javapid.repository.PidRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NivoDataService {

	private final PidRepository repository;

	public NivoDataService(PidRepository repository) {
		this.repository = repository;
	}

	public List<NivoLineAbstractData> getNivoLineData(List<String> validities){
		List<NivoLineAbstractData> personList = new ArrayList<>();
		validities = createDefaultValidityList(validities);
		personList.add(new NivoLineAdultData(repository.getAdultSum(validities)));
		personList.add(new NivoLineStudentData(repository.getStudentSum(validities)));
		personList.add(new NivoLineSeniorData(repository.getSeniorSum(validities)));
		personList.add(new NivoLineJuniorData(repository.getJuniorSum(validities)));
		personList.add(new NivoLinePortableData(repository.getPortableSum(validities)));
		return personList;
	}

	public List<NivoBarData> getNivoBarData(List<String> validities){
		validities = createDefaultValidityList(validities);
		return repository.getNivoBarData(validities);
	}

	public List<NivoPieAbstractData> getNivoPieData(List<String> validities){
		validities = createDefaultValidityList(validities);

		DataSumDTO pieData = repository.getNivoPieData(validities);
		List<NivoPieAbstractData> outputData = new ArrayList<>();
		outputData.add(new NivoPieAdultData(pieData.getAdults()));
		outputData.add(new NivoPieStudentData(pieData.getStudents()));
		outputData.add(new NivoPieSeniorData(pieData.getSeniors()));
		outputData.add(new NivoPieJuniorData(pieData.getJuniors()));
		outputData.add(new NivoPiePortableData(pieData.getPortable()));
		return outputData;
	}

	private List<String> createDefaultValidityList(List<String> validities) {
		if(validities == null){
			validities = Arrays.asList(Validity.MONTHLY.getValue(), Validity.YEARLY.getValue(), Validity.YEARLY.getValue(), Validity.YEARLY.getValue());
		}
		return validities;
	}
}
