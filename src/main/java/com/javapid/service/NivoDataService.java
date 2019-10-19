package com.javapid.service;

import com.javapid.entity.enums.JizdenkyTypes;
import com.javapid.entity.enums.PersonType;
import com.javapid.entity.nivo.DataSumDTO;
import com.javapid.entity.nivo.*;
import com.javapid.entity.nivo.line.*;
import com.javapid.entity.nivo.pie.*;
import com.javapid.repository.PidJizdenkyRepository;
import com.javapid.repository.PidRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.javapid.service.Validators.*;

@Service
public class NivoDataService {

	private final PidRepository repository;

	private final PidJizdenkyRepository jizdenkyRepository;

	public NivoDataService(PidRepository repository, PidJizdenkyRepository jizdenkyRepository) {
		this.repository = repository;
		this.jizdenkyRepository = jizdenkyRepository;
	}

	public List<NivoLineAbstractData> getNivoLineData(List<String> validities, List<String> sellTypes, List<String> months, List<String> year, List<String> personTypes) {
		List<NivoLineAbstractData> personList = new ArrayList<>();

		validities = verifyValidityList(validities);
		sellTypes = verifySellTypeList(sellTypes);
		months = verifyMonthsList(months);

		if (isPersonTypeRequested(personTypes, PersonType.ADULT.getValue())) {
			personList.add(new NivoLineAdultData(repository.getAdultSum(validities, sellTypes, months, verifyYears(year))));
		}

		if (isPersonTypeRequested(personTypes, PersonType.STUDENT.getValue())) {
			personList.add(new NivoLineStudentData(repository.getStudentSum(validities, sellTypes, months, verifyYears(year))));
		}

		if (isPersonTypeRequested(personTypes, PersonType.SENIOR.getValue())) {
			personList.add(new NivoLineSeniorData(repository.getSeniorSum(validities, sellTypes, months, verifyYears(year))));
		}

		if (isPersonTypeRequested(personTypes, PersonType.JUNIOR.getValue())) {
			personList.add(new NivoLineJuniorData(repository.getJuniorSum(validities, sellTypes, months, verifyYears(year))));
		}

		if (isPersonTypeRequested(personTypes, PersonType.PORTABLE.getValue())) {
			personList.add(new NivoLinePortableData(repository.getPortableSum(validities, sellTypes, months, verifyYears(year))));
		}
		return personList;
	}

	public List<NivoBarData> getNivoBarData(List<String> validities, List<String> sellTypes, List<String> months, List<String> year) {

		validities = verifyValidityList(validities);
		sellTypes = verifySellTypeList(sellTypes);
		months = verifyMonthsList(months);

		return repository.getNivoBarData(validities, sellTypes, months, verifyYears(year));
	}

	public List<NivoPieAbstractData> getNivoPieData(List<String> validities, List<String> sellTypes, List<String> months, List<String> year, List<String> personTypes) {

		validities = verifyValidityList(validities);
		sellTypes = verifySellTypeList(sellTypes);
		months = verifyMonthsList(months);

		DataSumDTO pieData = repository.getNivoPieData(validities, sellTypes, months, verifyYears(year));
		List<NivoPieAbstractData> outputData = new ArrayList<>();

		if (isPersonTypeRequested(personTypes, PersonType.ADULT.getValue())) {
			outputData.add(new NivoPieAdultData(pieData.getAdults()));
		}

		if (isPersonTypeRequested(personTypes, PersonType.STUDENT.getValue())) {
			outputData.add(new NivoPieStudentData(pieData.getStudents()));
		}

		if (isPersonTypeRequested(personTypes, PersonType.SENIOR.getValue())) {
			outputData.add(new NivoPieSeniorData(pieData.getSeniors()));
		}

		if (isPersonTypeRequested(personTypes, PersonType.JUNIOR.getValue())) {
			outputData.add(new NivoPieJuniorData(pieData.getJuniors()));
		}

		if (isPersonTypeRequested(personTypes, PersonType.PORTABLE.getValue())) {
			outputData.add(new NivoPiePortableData(pieData.getPortable()));
		}
		return outputData;
	}

	public List<NivoLineAbstractData> getJizdenyLineData(List<Boolean> discounted, List<String> months, List<String> year, List<String> jizdenkyTypes) {
		List<NivoLineAbstractData> personList = new ArrayList<>();

		discounted = verifyDiscountedList(discounted);
		months = verifyMonthsList(months);

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.FIFTEEN_MINUTES.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.FIFTEEN_MINUTES.getValue(), jizdenkyRepository.getFifteenMinutes(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.ONE_DAY.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.ONE_DAY.getValue(), jizdenkyRepository.getOneDay(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.ONE_DAY_ALL.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.ONE_DAY_ALL.getValue(), jizdenkyRepository.getOneDayAll(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.TWO_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.TWO_ZONES.getValue(), jizdenkyRepository.getTwoZones(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.THREE_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.THREE_ZONES.getValue(), jizdenkyRepository.getThreeZone(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.FOUR_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.FOUR_ZONES.getValue(), jizdenkyRepository.getFourZone(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.FIVE_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.FIVE_ZONES.getValue(), jizdenkyRepository.getFiveZone(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.SIX_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.SIX_ZONES.getValue(), jizdenkyRepository.getSixZone(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.SEVEN_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.SEVEN_ZONES.getValue(), jizdenkyRepository.getSevenZone(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.EIGHT_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.EIGHT_ZONES.getValue(), jizdenkyRepository.getEightZone(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.NINE_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.NINE_ZONES.getValue(), jizdenkyRepository.getNineZone(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.TEN_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.TEN_ZONES.getValue(), jizdenkyRepository.getTenZone(discounted, months, verifyYears(year))));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.ELEVEN_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(JizdenkyTypes.ELEVEN_ZONES.getValue(), jizdenkyRepository.getElevenZone(discounted, months, verifyYears(year))));
		}
		return personList;
	}

	public List<NivoJizdenkyBarData> getJizdenkyBarData(List<Boolean> discounted, List<String> months, List<String> year) {
		discounted = verifyDiscountedList(discounted);
		months = verifyMonthsList(months);

		return jizdenkyRepository.getJizdenkyBarData(discounted, months, verifyYears(year));
	}

	public List<NivoPieAbstractData> getJizdenkyPieData(List<Boolean> discounted, List<String> months, List<String> year, List<String> jizdenkyTypes) {
		discounted = verifyDiscountedList(discounted);
		months = verifyMonthsList(months);

		DataSumJizdenkyDTO pieData = jizdenkyRepository.getJizdenkyPieData(discounted, months, verifyYears(year));
		List<NivoPieAbstractData> outputData = new ArrayList<>();

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.FIFTEEN_MINUTES.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.FIFTEEN_MINUTES.getValue(), pieData.getFifteenMinutes()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.ONE_DAY.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.ONE_DAY.getValue(), pieData.getOneDay()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.ONE_DAY_ALL.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.ONE_DAY_ALL.getValue(), pieData.getOneDayAll()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.TWO_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.TWO_ZONES.getValue(), pieData.getTwoZones()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.THREE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.THREE_ZONES.getValue(), pieData.getThreeZones()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.FOUR_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.FOUR_ZONES.getValue(), pieData.getFourZones()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.FIVE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.FIVE_ZONES.getValue(), pieData.getFiveZones()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.SIX_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.SIX_ZONES.getValue(), pieData.getSixZones()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.SEVEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.SEVEN_ZONES.getValue(), pieData.getSevenZones()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.TEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.EIGHT_ZONES.getValue(), pieData.getEightZones()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.NINE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.NINE_ZONES.getValue(), pieData.getNineZones()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.TEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.TEN_ZONES.getValue(), pieData.getTenZones()));
		}

		if(isJizdenkyType(jizdenkyTypes,JizdenkyTypes.ELEVEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(JizdenkyTypes.ELEVEN_ZONES.getValue(), pieData.getElevenZones()));
		}
		return outputData;
	}
}
