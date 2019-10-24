package com.javapid.service;

import com.javapid.entity.enums.PersonType;
import com.javapid.entity.enums.Validity;
import com.javapid.entity.nivo.NivoBarDataSumDTO;
import com.javapid.entity.nivo.*;
import com.javapid.entity.nivo.line.*;
import com.javapid.entity.nivo.pie.*;
import com.javapid.objects.recharts.*;
import com.javapid.repository.PidCouponsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.javapid.service.Validators.*;

@Service
public class PidCouponsService {

	private final PidCouponsRepository repository;

	public PidCouponsService(PidCouponsRepository repository) {
		this.repository = repository;
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
		List<Integer> verifiedYears = verifyYears(year);

		if (isPersonTypeRequested(personTypes, PersonType.ADULT.getValue())) {
			personList.add(new NivoLineAdultData(repository.getAdultSum(validities, sellTypes, months, verifiedYears)));
		}

		if (isPersonTypeRequested(personTypes, PersonType.STUDENT.getValue())) {
			personList.add(new NivoLineStudentData(repository.getStudentSum(validities, sellTypes, months, verifiedYears)));
		}

		if (isPersonTypeRequested(personTypes, PersonType.SENIOR.getValue())) {
			personList.add(new NivoLineSeniorData(repository.getSeniorSum(validities, sellTypes, months, verifiedYears)));
		}

		if (isPersonTypeRequested(personTypes, PersonType.JUNIOR.getValue())) {
			personList.add(new NivoLineJuniorData(repository.getJuniorSum(validities, sellTypes, months, verifiedYears)));
		}

		if (isPersonTypeRequested(personTypes, PersonType.PORTABLE.getValue())) {
			personList.add(new NivoLinePortableData(repository.getPortableSum(validities, sellTypes, months, verifiedYears)));
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
		List<NivoBarDataByMonth> barData = repository.getNivoBarDataByValidity(validity, verifySellTypeList(sellTypes), verifyMonthsList(months), verifyYears(year));
		List<DataXY> test = barData.stream()
				.map(element -> new DataXY(element.getMonth(), getDataSum(element, personTypes)))
				.collect(Collectors.toList());
		lineData.setData(test);
	}

	private Long getDataSum(NivoBarDataByMonth element, List<String> personTypes) {
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

	public List<NivoBarDataByMonth> getNivoBarData(List<String> validities, List<String> sellTypes, List<String> months, List<String> year, List<String> personTypes) {
		List<NivoBarDataByMonth> dataList = repository.getNivoBarData(verifyValidityList(validities), verifySellTypeList(sellTypes), verifyMonthsList(months), verifyYears(year));
		dataList
				.forEach(element -> {
					if (!isPersonTypeRequested(personTypes, PersonType.ADULT.getValue())) {
						element.setAdults(0L);
					}

					if (!isPersonTypeRequested(personTypes, PersonType.SENIOR.getValue())) {
						element.setSeniors(0L);
					}

					if (!isPersonTypeRequested(personTypes, PersonType.JUNIOR.getValue())) {
						element.setJuniors(0L);
					}

					if (!isPersonTypeRequested(personTypes, PersonType.STUDENT.getValue())) {
						element.setStudents(0L);
					}

					if (!isPersonTypeRequested(personTypes, PersonType.PORTABLE.getValue())) {
						element.setPortable(0L);
					}
				});
		return dataList;
	}

	public List<NivoBarDataByValidity> getNivoBarDataByValidity(List<String> sellTypes, List<String> months, List<String> year, List<String> personTypes) {
		return verifyValidityList(null).stream()
				.map(element -> getBarDataFromRepository(element, sellTypes, months, year, personTypes))
				.collect(Collectors.toList());
	}

	private NivoBarDataByValidity getBarDataFromRepository(String validity, List<String> sellTypes, List<String> months, List<String> year, List<String> personTypes) {
		AtomicReference<Long> adults = new AtomicReference<>(0L);
		AtomicReference<Long> juniors = new AtomicReference<>(0L);
		AtomicReference<Long> seniors = new AtomicReference<>(0L);
		AtomicReference<Long> students = new AtomicReference<>(0L);
		AtomicReference<Long> portable = new AtomicReference<>(0L);

		repository.getNivoBarDataByValidity(validity, verifySellTypeList(sellTypes), verifyMonthsList(months), verifyYears(year))
				.forEach(element -> {
					if (isPersonTypeRequested(personTypes, PersonType.ADULT.getValue())) {
						adults.updateAndGet(v -> v + element.getAdults());
					}

					if (isPersonTypeRequested(personTypes, PersonType.JUNIOR.getValue())) {
						juniors.updateAndGet(v -> v + element.getJuniors());
					}

					if (isPersonTypeRequested(personTypes, PersonType.SENIOR.getValue())) {
						seniors.updateAndGet(v -> v + element.getSeniors());
					}

					if (isPersonTypeRequested(personTypes, PersonType.STUDENT.getValue())) {
						students.updateAndGet(v -> v + element.getStudents());
					}

					if (isPersonTypeRequested(personTypes, PersonType.PORTABLE.getValue())) {
						portable.updateAndGet(v -> v + element.getPortable());
					}
				});

		return new NivoBarDataByValidity(validity, adults.get(), seniors.get(), juniors.get(), students.get(), portable.get());
	}


	public List<NivoPieAbstractData> getNivoPieData(List<String> validities, List<String> sellTypes, List<String> months, List<String> year, List<String> personTypes) {

		validities = verifyValidityList(validities);
		sellTypes = verifySellTypeList(sellTypes);
		months = verifyMonthsList(months);

		NivoBarDataSumDTO pieData = repository.getNivoPieData(validities, sellTypes, months, verifyYears(year));
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

	public List<List<PersonAbstractClass>> getPersonData(List<String> validations, List<String> sellTypes, List<String> months, List<String> year) {
		List<NivoBarDataByMonth> dataList = repository.getNivoBarData(verifyValidityList(validations), verifySellTypeList(sellTypes), verifyMonthsList(months), verifyYears(year));
		return dataList.stream()
				.map(this::createPeronList)
				.collect(Collectors.toList());
	}

	private List<PersonAbstractClass> createPeronList(NivoBarDataByMonth data) {
		List<PersonAbstractClass> personsList = new ArrayList<>();
		String month = data.getMonth();
		personsList.add(new AdultObject(month, data.getAdults()));
		personsList.add(new JuniorObject(month, data.getJuniors()));
		personsList.add(new SeniorObject(month, data.getSeniors()));
		personsList.add(new PortableObject(month, data.getPortable()));
		personsList.add(new StudentObject(month, data.getStudents()));
		return personsList;
	}
}
