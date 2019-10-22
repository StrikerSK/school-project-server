package com.javapid.service;

import com.javapid.entity.enums.TicketTypes;
import com.javapid.entity.enums.PersonType;
import com.javapid.entity.enums.Validity;
import com.javapid.entity.nivo.DataSumDTO;
import com.javapid.entity.nivo.*;
import com.javapid.entity.nivo.line.*;
import com.javapid.entity.nivo.pie.*;
import com.javapid.repository.PidJizdenkyRepository;
import com.javapid.repository.PidRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.javapid.service.Validators.*;

@Service
public class NivoDataService {

	private final PidRepository repository;

	private final PidJizdenkyRepository jizdenkyRepository;

	public NivoDataService(PidRepository repository, PidJizdenkyRepository jizdenkyRepository) {
		this.repository = repository;
		this.jizdenkyRepository = jizdenkyRepository;
	}

	/**
	 * Method gets pid data adapted to Nivo Line data format
	 *
	 * @param validities  - list of relevancy requested by client
	 * @param sellTypes   - list of ticket sell types requested by user
	 * @param months      - list of months requested by user
	 * @param year        - list of years requested by user
	 * @param personTypes - list of person types requested by user
	 */
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

	public List<NivoGeneralLineData> getNivoLineDataByValidity(final List<String> sellTypes, final List<String> months, List<String> year, List<String> personTypes) {
		List<NivoGeneralLineData> personList = Arrays.asList(
				new NivoGeneralLineData(Validity.MONTHLY.getValue()),
				new NivoGeneralLineData(Validity.THREE_MONTHS.getValue()),
				new NivoGeneralLineData(Validity.FIVE_MONTHS.getValue()),
				new NivoGeneralLineData(Validity.YEARLY.getValue())
		);

		personList.forEach(element -> generateLineData(element, element.getId(), sellTypes, months, year, personTypes));
		return personList;
	}

	private void generateLineData(NivoGeneralLineData lineData, String validity, List<String> sellTypes, final List<String> months, List<String> year, List<String> personTypes) {
		List<NivoBarData> barData = repository.getNivoBarDataByValidity(validity, verifySellTypeList(sellTypes), verifyMonthsList(months), verifyYears(year));
		List<DataXY> test = barData.stream()
				.map(element -> new DataXY(element.getMonth(), getDataSum(element, personTypes)))
				.collect(Collectors.toList());
		lineData.setData(test);
	}

	private Long getDataSum(NivoBarData element, List<String> personTypes) {
		Long dataSum = 0L;

		if (isPersonTypeRequested(personTypes, PersonType.ADULT.getValue())) {
			dataSum += element.getAdults();
		}

		if (isPersonTypeRequested(personTypes, PersonType.JUNIOR.getValue())) {
			dataSum += element.getJuniors();
		}

		if (isPersonTypeRequested(personTypes, PersonType.SENIOR.getValue())) {
			dataSum += element.getSeniors();
		}

		if (isPersonTypeRequested(personTypes, PersonType.STUDENT.getValue())) {
			dataSum += element.getStudents();
		}

		if (isPersonTypeRequested(personTypes, PersonType.PORTABLE.getValue())) {
			dataSum += element.getPortable();
		}

		return dataSum;
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

	public List<NivoGeneralPieData> getNivoPieDataByValidity(List<String> sellTypes, List<String> months, List<String> year, List<String> personTypes) {
		List<NivoGeneralPieData> outputData = Arrays.asList(
				new NivoGeneralPieData(Validity.MONTHLY.getValue()),
				new NivoGeneralPieData(Validity.THREE_MONTHS.getValue()),
				new NivoGeneralPieData(Validity.FIVE_MONTHS.getValue()),
				new NivoGeneralPieData(Validity.YEARLY.getValue())
		);
		outputData.forEach(element -> element.setValue(setPieValidityValue(element.getId(), sellTypes, months, year, personTypes)));
		return outputData;
	}

	private Long setPieValidityValue(String validity, List<String> sellTypes, List<String> months, List<String> year, List<String> personTypes) {
		return repository.getNivoBarDataByValidity(validity, verifySellTypeList(sellTypes), verifyMonthsList(months), verifyYears(year))
				.stream()
				.mapToLong(test -> getDataSum(test, personTypes)).sum();
	}

	public List<NivoLineAbstractData> getJizdenyLineData(List<Boolean> discounted, List<String> months, List<String> year, List<String> ticketTypes) {
		List<NivoLineAbstractData> personList = new ArrayList<>();

		discounted = verifyDiscountedList(discounted);
		months = verifyMonthsList(months);

		if (isTicketTypeRequested(ticketTypes, TicketTypes.FIFTEEN_MINUTES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.FIFTEEN_MINUTES.getValue(), jizdenkyRepository.getFifteenMinutes(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.ONE_DAY.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.ONE_DAY.getValue(), jizdenkyRepository.getOneDay(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.ONE_DAY_ALL.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.ONE_DAY_ALL.getValue(), jizdenkyRepository.getOneDayAll(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.TWO_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.TWO_ZONES.getValue(), jizdenkyRepository.getTwoZones(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.THREE_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.THREE_ZONES.getValue(), jizdenkyRepository.getThreeZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.FOUR_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.FOUR_ZONES.getValue(), jizdenkyRepository.getFourZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.FIVE_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.FIVE_ZONES.getValue(), jizdenkyRepository.getFiveZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.SIX_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.SIX_ZONES.getValue(), jizdenkyRepository.getSixZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.SEVEN_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.SEVEN_ZONES.getValue(), jizdenkyRepository.getSevenZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.EIGHT_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.EIGHT_ZONES.getValue(), jizdenkyRepository.getEightZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.NINE_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.NINE_ZONES.getValue(), jizdenkyRepository.getNineZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.TEN_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.TEN_ZONES.getValue(), jizdenkyRepository.getTenZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.ELEVEN_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.ELEVEN_ZONES.getValue(), jizdenkyRepository.getElevenZone(discounted, months, verifyYears(year))));
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

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.FIFTEEN_MINUTES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.FIFTEEN_MINUTES.getValue(), pieData.getFifteenMinutes()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.ONE_DAY.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.ONE_DAY.getValue(), pieData.getOneDay()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.ONE_DAY_ALL.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.ONE_DAY_ALL.getValue(), pieData.getOneDayAll()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.TWO_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.TWO_ZONES.getValue(), pieData.getTwoZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.THREE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.THREE_ZONES.getValue(), pieData.getThreeZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.FOUR_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.FOUR_ZONES.getValue(), pieData.getFourZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.FIVE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.FIVE_ZONES.getValue(), pieData.getFiveZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.SIX_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.SIX_ZONES.getValue(), pieData.getSixZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.SEVEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.SEVEN_ZONES.getValue(), pieData.getSevenZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.TEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.EIGHT_ZONES.getValue(), pieData.getEightZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.NINE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.NINE_ZONES.getValue(), pieData.getNineZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.TEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.TEN_ZONES.getValue(), pieData.getTenZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.ELEVEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.ELEVEN_ZONES.getValue(), pieData.getElevenZones()));
		}
		return outputData;
	}
}
