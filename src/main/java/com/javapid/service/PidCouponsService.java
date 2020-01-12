package com.javapid.service;

import com.javapid.entity.ApexchartsData;
import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.enums.PersonType;
import com.javapid.entity.nivo.DataXY;
import com.javapid.entity.nivo.bar.*;
import com.javapid.entity.nivo.bubble.BubbleChartData;
import com.javapid.entity.nivo.bubble.NivoBubbleAbstract;
import com.javapid.entity.nivo.bubble.NivoBubbleData;
import com.javapid.entity.nivo.line.NivoGeneralLineData;
import com.javapid.entity.nivo.line.NivoLineAbstractData;
import com.javapid.entity.nivo.pie.NivoGeneralPieData;
import com.javapid.entity.nivo.pie.NivoPieAbstractData;
import com.javapid.objects.recharts.PersonAbstractClass;
import com.javapid.repository.JdbcCouponRepository;
import com.javapid.repository.PidCouponsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.javapid.service.Validators.*;

@Service
public class PidCouponsService {

	private final PidCouponsRepository repository;
	private final JdbcCouponRepository jdbcCouponRepository;
	private static final Logger LOGGER = Logger.getLogger("Coupon service");

	public PidCouponsService(PidCouponsRepository repository, JdbcCouponRepository jdbcCouponRepository) {
		this.repository = repository;
		this.jdbcCouponRepository = jdbcCouponRepository;
	}

	public List<NivoLineAbstractData> getNivoLineData(PidCouponsParameters parameters) {
		return verifyPersonList(parameters.getPerson()).stream()
				.map(element -> new NivoGeneralLineData(element, jdbcCouponRepository.fetchCouponLineData(findColumnByValue(element), parameters)))
				.collect(Collectors.toList());
	}

	/**
	 * Method gets line data by validity
	 *
	 * @param parameters all requested parameters
	 * @return data for displaying line chart by validity
	 */
	public List<NivoLineAbstractData> getNivoLineDataByValidity(PidCouponsParameters parameters) {
		List<NivoLineAbstractData> outputValidityData = new ArrayList<>();
		for (String validity : parameters.getValidity()) {
			NivoGeneralLineData singleElement = new NivoGeneralLineData(validity);
			List<DataXY> outputBarData = getAllSumsRow(parameters, validity).stream()
					.map(element -> new DataXY(element.getMonth(), getDataSum(element, parameters.getPerson())))
					.collect(Collectors.toList());
			singleElement.setData(outputBarData);
			outputValidityData.add(singleElement);
		}
		return outputValidityData;
	}

	/**
	 * Method sums data row of provided line
	 *
	 * @param element     - represents line of retrieved data
	 * @param personTypes - represents requested person types
	 * @return sum of all values needed
	 */
	private Long getDataSum(NivoBarDataAbstract element, List<String> personTypes) {
		Long dataSum = 0L;
		String person = "";

		for (PersonType personType : PersonType.values()) {
			try {
				if (isPersonTypeRequested(personTypes, personType.value)) {
					person = personType.methodValue;
					dataSum += (Long) element.getClass().getMethod("get" + person).invoke(element);
				}
			} catch (NoSuchMethodException e) {
				LOGGER.warning(String.format("There is no such method get%s()", person));
			} catch (Exception e) {
				LOGGER.warning("There was an error");
			}
		}
		return dataSum;
	}

	public List<NivoBarDataByMonth> getNivoBarData(PidCouponsParameters parameters) {
		List<NivoBarDataByMonth> dataList = getAllSumsRow(parameters);
		dataList.forEach(element -> {
			for (PersonType personType : PersonType.values()) {
				try {
					if (!isPersonTypeRequested(parameters.getPerson(), personType.value)) {
						String person = personType.methodValue;
						element.getClass().getMethod("set" + person, Long.class).invoke(element, 0L);
					}
				} catch (Exception e) {
					LOGGER.warning("There was an error");
				}
			}
		});
		return dataList;
	}

	public List<NivoBarDataByValidity> getNivoBarDataByValidity(PidCouponsParameters parameters) {
		return verifyValidityList(Collections.emptyList()).stream()
				.map(validity -> getBarDataFromRepository(validity, parameters))
				.collect(Collectors.toList());
	}

	public List<NivoBarDataMonthsByValidity> getMonthsByValidity(PidCouponsParameters parameters) {
		List<NivoBarDataMonthsByValidity> outputData = new ArrayList<>();
		for (String validity : parameters.getValidity()) {
			NivoBarDataMonthsByValidity outputObject = new NivoBarDataMonthsByValidity(validity);
			for (String month : parameters.getMonth()) {
				NivoBarDataByMonth currentBarData = getAllSumsRow(parameters, Collections.singletonList(validity), null, Collections.singletonList(month), null).get(0);
				outputObject.setData(month, getDataSum(currentBarData, parameters.getPerson()));
			}
			outputData.add(outputObject);
		}
		return outputData;
	}

	public List<NivoBarDataValidityByMonth> getValidityByMonth(PidCouponsParameters parameters) {
		List<NivoBarDataValidityByMonth> outputList = new ArrayList<>();
		for (String month : parameters.getMonth()) {
			NivoBarDataValidityByMonth validityBarData = new NivoBarDataValidityByMonth(month);
			for (String validity : parameters.getValidity()) {
				NivoBarDataByMonth monthData = getAllSumsRow(parameters, Collections.singletonList(validity), null, Collections.singletonList(month), null).get(0);
				validityBarData.setData(validity, getDataSum(monthData, parameters.getPerson()));
			}
			outputList.add(validityBarData);
		}
		return outputList;
	}

	private NivoBarDataByValidity getBarDataFromRepository(String validity, PidCouponsParameters parameters) {
		NivoBarDataByValidity outputData = new NivoBarDataByValidity(validity);

		for (NivoBarDataAbstract element : getAllSumsRow(parameters, validity)) {
			for (PersonType personType : PersonType.values()) {
				try {
					if (isPersonTypeRequested(parameters.getPerson(), personType.value)) {
						String person = personType.methodValue;
						Long receivedValue = (Long) element.getClass().getMethod("get" + person).invoke(element);
						outputData.getClass().getMethod("addTo" + person, Long.class).invoke(outputData, receivedValue);
					}
				} catch (Exception e) {
					LOGGER.warning("There was an error");
				}
			}
		}
		return outputData;
	}


	public List<NivoPieAbstractData> getNivoPieData(PidCouponsParameters parameters) {
		NivoBarDataSumDTO pieData = repository.getNivoPieData(parameters.getValidity(), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
		List<NivoPieAbstractData> outputData = new ArrayList<>();

		for (PersonType personType : PersonType.values()) {
			try {
				if (isPersonTypeRequested(parameters.getPerson(), personType.value)) {
					String person = personType.methodValue;
					Long receivedValue = (Long) pieData.getClass().getMethod("get" + person).invoke(pieData);

					NivoPieAbstractData newData = new NivoPieAbstractData(personType.value, receivedValue);
					outputData.add(newData);
				}
			} catch (Exception e) {
				LOGGER.warning("There was an error");
			}
		}
		return outputData;
	}

	public List<NivoGeneralPieData> getNivoPieDataByValidity(PidCouponsParameters parameters) {
		List<NivoGeneralPieData> outputData = verifyValidityList(Collections.emptyList()).stream().map(NivoGeneralPieData::new).collect(Collectors.toList());
		outputData.forEach(element -> element.setValue(setPieValidityValue(element.getId(), parameters)));
		return outputData;
	}

	private Long setPieValidityValue(String validity, PidCouponsParameters parameters) {
		return getAllSumsRow(parameters, validity).stream()
				.mapToLong(element -> getDataSum(element, parameters.getPerson()))
				.sum();
	}

	public BubbleChartData getNivoBubbleChart(PidCouponsParameters parameters) {
		BubbleChartData outputData = new BubbleChartData("Predaj kupónov");
		for (String personType : parameters.getPerson()) {
			BubbleChartData.FirstChildren firstChildren = new BubbleChartData.FirstChildren(personType);
			String personColumn = findColumnByValue(personType);
			for (String couponType : parameters.getValidity()) {
				firstChildren.addSecondChildren(couponType, jdbcCouponRepository.fetchBubbleData(personColumn, couponType, parameters));
			}
			outputData.addFirstChildren(firstChildren);
		}
		return outputData;
	}

	public BubbleChartData getNivoBubbleChartByValidity(PidCouponsParameters parameters) {
		BubbleChartData outputData = new BubbleChartData("Predaj kupónov");
		for (String couponType : parameters.getValidity()) {
			BubbleChartData.FirstChildren children = new BubbleChartData.FirstChildren(couponType);
			NivoBarDataSumDTO data = repository.getNivoPieData(Collections.singletonList(couponType), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
			for (PersonType personType : PersonType.values()) {
				try {
					if (isPersonTypeRequested(parameters.getPerson(), personType.value)) {
						String person = personType.methodValue;
						Long receivedValue = (Long) data.getClass().getMethod("get" + person).invoke(data);
						children.addSecondChildren(personType.getValue(), receivedValue);
					}
				} catch (Exception e) {
					LOGGER.warning("There was an error");
				}
			}
			outputData.addFirstChildren(children);
		}
		return outputData;
	}

	public NivoBubbleAbstract getNivoBubbleChartExperimental(PidCouponsParameters parameters) {
		NivoBubbleData outputData = new NivoBubbleData("Predaj kupónov");
		for (String month : parameters.getMonth()) {
			NivoBubbleData.FirstComplexChildren firstComplexChildren = new NivoBubbleData.FirstComplexChildren(month);
			for (String person : parameters.getPerson()) {
				NivoBubbleData.FirstComplexChildren.SecondComplexChildren secondComplexChildren = new NivoBubbleData.FirstComplexChildren.SecondComplexChildren(person);
				for (String validity : parameters.getValidity()) {
					Long sum = jdbcCouponRepository.fetchBubbleData(findColumnByValue(person), validity, month, parameters);
					secondComplexChildren.addToList(validity, sum);
				}
				firstComplexChildren.addChildren(secondComplexChildren);
			}
			outputData.addChildren(firstComplexChildren);
		}
		return outputData;
	}

	String findColumnByValue(String lookedPerson) {
		for (PersonType personType : PersonType.values()) {
			if (personType.getValue().equals(lookedPerson)) {
				return personType.getColumn();
			}
		}
		return null;
	}

	/**
	 * Method fetches and adjust data to Recharts module
	 *
	 * @return data adapted to Recharts module
	 */
	public List<List<PersonAbstractClass>> getRechartsData(PidCouponsParameters parameters) {
		return getAllSumsRow(parameters).stream()
				.map(element -> createPersonList(element, parameters.getPerson()))
				.collect(Collectors.toList());
	}

	private List<PersonAbstractClass> createPersonList(NivoBarDataByMonth data, List<String> personTypes) {
		List<PersonAbstractClass> personsList = new ArrayList<>();
		String month = data.getMonth();

		for (PersonType personType : PersonType.values()) {
			try {
				if (isPersonTypeRequested(personTypes, personType.value)) {
					String person = personType.methodValue;
					Long receivedValue = (Long) data.getClass().getMethod("get" + person).invoke(data);
					personsList.add(new PersonAbstractClass(personType.value, month, receivedValue));
				}
			} catch (Exception e) {
				LOGGER.warning("There was an error");
			}
		}
		return personsList;
	}

	/**
	 * Method retrieves Apex data by validity
	 */
	public List<ApexchartsData> getApexDataByValidity(PidCouponsParameters parameters) {
		List<ApexchartsData> outputData = new ArrayList<>();
		for (String validity : parameters.getValidity()) {
			List<Long> dataSum = getAllSumsRow(parameters, validity).stream().map(e -> getDataSum(e, parameters.getPerson())).collect(Collectors.toList());
			ApexchartsData newDataLine = new ApexchartsData(validity, dataSum);
			outputData.add(newDataLine);
		}
		return outputData;
	}

	private List<NivoBarDataByMonth> getAllSumsRow(final PidCouponsParameters parameters, String value) {
		return repository.getNivoBarDataByValidity(value, parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
	}

	private List<NivoBarDataByMonth> getAllSumsRow(final PidCouponsParameters parameters) {
		return repository.getNivoBarData(parameters.getValidity(), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
	}

	private List<NivoBarDataByMonth> getAllSumsRow(final PidCouponsParameters parameters, List<String> validity, List<String> sellType, List<String> month, List<Integer> year) {

		if (validity == null) {
			validity = parameters.getValidity();
		}

		if (sellType == null) {
			sellType = parameters.getSellType();
		}

		if (month == null) {
			month = parameters.getMonth();
		}

		if (year == null) {
			year = parameters.getYearInteger();
		}

		return repository.getNivoBarData(validity, sellType, month, year);
	}
}
