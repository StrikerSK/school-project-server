package com.javapid.service;

import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.enums.PersonType;
import com.javapid.entity.enums.Validity;
import com.javapid.entity.nivo.bar.NivoBarDataByMonth;
import com.javapid.entity.nivo.bar.NivoBarDataByValidity;
import com.javapid.entity.nivo.bar.NivoBarDataSumDTO;
import com.javapid.entity.nivo.*;
import com.javapid.entity.nivo.line.*;
import com.javapid.entity.nivo.pie.*;
import com.javapid.objects.recharts.*;
import com.javapid.repository.CouponRepository;
import com.javapid.repository.PidCouponsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.javapid.service.Validators.*;

@Service
public class PidCouponsService {

	private final PidCouponsRepository repository;

	private final CouponRepository couponRepository;

	public PidCouponsService(PidCouponsRepository repository, CouponRepository couponRepository) {
		this.repository = repository;
		this.couponRepository = couponRepository;
	}

	public List<NivoLineAbstractData> getNivoLineData(PidCouponsParameters parameters) {

		return verifyPersonList(parameters.getPerson()).stream()
				.map(element -> new NivoGeneralLineData(element, couponRepository.fetchCouponLineData(getColumnName(element), parameters)))
				.collect(Collectors.toList());
	}

	public List<NivoGeneralLineData> getNivoLineDataByValidity(PidCouponsParameters parameters) {
		List<NivoGeneralLineData> personList = verifyPersonList(Collections.emptyList()).stream()
				.map(NivoGeneralLineData::new)
				.collect(Collectors.toList());

		personList.forEach(element -> generateLineData(element, element.getId(), parameters));
		return personList;
	}

	private void generateLineData(NivoGeneralLineData lineData, String validity, PidCouponsParameters parameters) {
		List<NivoBarDataByMonth> barData = repository.getNivoBarDataByValidity(validity, parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
		List<DataXY> outputBarData = barData.stream()
				.map(element -> new DataXY(element.getMonth(), getDataSum(element, parameters.getPerson())))
				.collect(Collectors.toList());
		lineData.setData(outputBarData);
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

	public List<NivoBarDataByMonth> getNivoBarData(PidCouponsParameters parameters) {
		List<NivoBarDataByMonth> dataList = repository.getNivoBarData(parameters.getValidity(), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
		dataList
				.forEach(element -> {
					if (!isPersonTypeRequested(parameters.getPerson(), PersonType.ADULT.getValue())) {
						element.setAdults(0L);
					}

					if (!isPersonTypeRequested(parameters.getPerson(), PersonType.SENIOR.getValue())) {
						element.setSeniors(0L);
					}

					if (!isPersonTypeRequested(parameters.getPerson(), PersonType.JUNIOR.getValue())) {
						element.setJuniors(0L);
					}

					if (!isPersonTypeRequested(parameters.getPerson(), PersonType.STUDENT.getValue())) {
						element.setStudents(0L);
					}

					if (!isPersonTypeRequested(parameters.getPerson(), PersonType.PORTABLE.getValue())) {
						element.setPortable(0L);
					}
				});
		return dataList;
	}

	public List<NivoBarDataByValidity> getNivoBarDataByValidity(PidCouponsParameters parameters) {
		return verifyValidityList(Collections.emptyList()).stream()
				.map(element -> getBarDataFromRepository(element, parameters))
				.collect(Collectors.toList());
	}

	private NivoBarDataByValidity getBarDataFromRepository(String validity, PidCouponsParameters parameters) {
		AtomicReference<Long> adults = new AtomicReference<>(0L);
		AtomicReference<Long> juniors = new AtomicReference<>(0L);
		AtomicReference<Long> seniors = new AtomicReference<>(0L);
		AtomicReference<Long> students = new AtomicReference<>(0L);
		AtomicReference<Long> portable = new AtomicReference<>(0L);
		List<String> personTypeList = parameters.getPerson();

		repository.getNivoBarDataByValidity(validity, parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger())
				.forEach(element -> {
					if (isPersonTypeRequested(personTypeList, PersonType.ADULT.getValue())) {
						adults.updateAndGet(v -> v + element.getAdults());
					}

					if (isPersonTypeRequested(personTypeList, PersonType.JUNIOR.getValue())) {
						juniors.updateAndGet(v -> v + element.getJuniors());
					}

					if (isPersonTypeRequested(personTypeList, PersonType.SENIOR.getValue())) {
						seniors.updateAndGet(v -> v + element.getSeniors());
					}

					if (isPersonTypeRequested(personTypeList, PersonType.STUDENT.getValue())) {
						students.updateAndGet(v -> v + element.getStudents());
					}

					if (isPersonTypeRequested(personTypeList, PersonType.PORTABLE.getValue())) {
						portable.updateAndGet(v -> v + element.getPortable());
					}
				});

		return new NivoBarDataByValidity(validity, adults.get(), seniors.get(), juniors.get(), students.get(), portable.get());
	}


	public List<NivoPieAbstractData> getNivoPieData(PidCouponsParameters parameters) {
		NivoBarDataSumDTO pieData = repository.getNivoPieData(parameters.getValidity(), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
		List<NivoPieAbstractData> outputData = new ArrayList<>();

		if (isPersonTypeRequested(parameters.getPerson(), PersonType.ADULT.getValue())) {
			outputData.add(new NivoPieAdultData(pieData.getAdults()));
		}

		if (isPersonTypeRequested(parameters.getPerson(), PersonType.STUDENT.getValue())) {
			outputData.add(new NivoPieStudentData(pieData.getStudents()));
		}

		if (isPersonTypeRequested(parameters.getPerson(), PersonType.SENIOR.getValue())) {
			outputData.add(new NivoPieSeniorData(pieData.getSeniors()));
		}

		if (isPersonTypeRequested(parameters.getPerson(), PersonType.JUNIOR.getValue())) {
			outputData.add(new NivoPieJuniorData(pieData.getJuniors()));
		}

		if (isPersonTypeRequested(parameters.getPerson(), PersonType.PORTABLE.getValue())) {
			outputData.add(new NivoPiePortableData(pieData.getPortable()));
		}
		return outputData;
	}

	public List<NivoGeneralPieData> getNivoPieDataByValidity(PidCouponsParameters parameters) {
		List<NivoGeneralPieData> outputData = verifyValidityList(Collections.emptyList()).stream().map(NivoGeneralPieData::new).collect(Collectors.toList());
		outputData.forEach(element -> element.setValue(setPieValidityValue(element.getId(), parameters)));
		return outputData;
	}

	private Long setPieValidityValue(String validity, PidCouponsParameters parameters) {
		return repository.getNivoBarDataByValidity(validity, parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger()).stream()
				.mapToLong(element -> getDataSum(element, parameters.getPerson())).sum();
	}

	public List<List<PersonAbstractClass>> getPersonData(PidCouponsParameters parameters) {
		return repository.getNivoBarData(parameters.getValidity(), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger()).stream()
				.map(element -> createPeronList(element, parameters.getPerson()))
				.collect(Collectors.toList());
	}

	private List<PersonAbstractClass> createPeronList(NivoBarDataByMonth data, List<String> personTypes) {
		List<PersonAbstractClass> personsList = new ArrayList<>();
		String month = data.getMonth();

		if (isPersonTypeRequested(personTypes, PersonType.ADULT.getValue())) {
			personsList.add(new AdultObject(month, data.getAdults()));
		}

		if (isPersonTypeRequested(personTypes, PersonType.JUNIOR.getValue())) {
			personsList.add(new JuniorObject(month, data.getJuniors()));
		}

		if (isPersonTypeRequested(personTypes, PersonType.SENIOR.getValue())) {
			personsList.add(new SeniorObject(month, data.getSeniors()));
		}

		if (isPersonTypeRequested(personTypes, PersonType.PORTABLE.getValue())) {
			personsList.add(new PortableObject(month, data.getPortable()));
		}

		if (isPersonTypeRequested(personTypes, PersonType.STUDENT.getValue())) {
			personsList.add(new StudentObject(month, data.getStudents()));
		}

		return personsList;
	}

	private String getColumnName(String name) {
		return Arrays.stream(PersonType.values())
				.filter(e -> name.equals(e.getValue()))
				.findFirst()
				.get()
				.getColumn();
	}
}
