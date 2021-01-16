package com.charts.general.service;

import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.nivo.DataXY;
import com.charts.general.entity.nivo.NivoLineData;
import com.charts.general.entity.nivo.NivoPieData;
import com.charts.general.entity.nivo.bar.NivoBarCouponData;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByValidity;
import com.charts.general.entity.nivo.bar.NivoBarMonthsDataByValidity;
import com.charts.general.entity.nivo.bar.NivoBarValidityDataByMonth;
import com.charts.general.entity.nivo.bubble.BubbleChartData;
import com.charts.general.entity.nivo.bubble.NivoBubbleAbstract;
import com.charts.general.entity.nivo.bubble.NivoBubbleData;
import com.charts.general.objects.recharts.PersonAbstractClass;
import com.charts.general.repository.JdbcCouponRepository;
import com.charts.general.repository.PidCouponsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PidCouponsService {

	private final PidCouponsRepository pidCouponsRepository;
	private final JdbcCouponRepository jdbcCouponRepository;
	private static final Logger LOGGER = Logger.getLogger("Coupon service");

	public PidCouponsService(PidCouponsRepository pidCouponsRepository, JdbcCouponRepository jdbcCouponRepository) {
		this.pidCouponsRepository = pidCouponsRepository;
		this.jdbcCouponRepository = jdbcCouponRepository;
	}

	public List<NivoLineData> getNivoLineData(PidCouponsParameters parameters) {
		return Validators.verifyPersonList(parameters.getPerson()).stream()
				.map(element -> new NivoLineData(element, jdbcCouponRepository.fetchCouponLineData(findColumnByValue(element), parameters)))
				.collect(Collectors.toList());
	}

	/**
	 * Method gets line data by validity
	 *
	 * @param parameters all requested parameters
	 * @return data for displaying line chart by validity
	 */
	public List<NivoLineData> getNivoLineDataByValidity(PidCouponsParameters parameters) {
		List<NivoLineData> outputValidityData = new ArrayList<>();
		for (String validity : parameters.getValidity()) {
			NivoLineData singleElement = new NivoLineData(validity);
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
	public Long getDataSum(NivoBarCouponData element, List<String> personTypes) {
		Long dataSum = 0L;
		String person = "";

		for (PersonType personType : PersonType.values()) {
			try {
				if (Validators.isPersonTypeRequested(personTypes, personType.value)) {
					person = personType.methodValue;
					dataSum += (Long) element.getClass().getMethod("get" + person).invoke(element);
				}
			} catch (NoSuchMethodException e) {
				LOGGER.warning(String.format("There is no such method get%s()", person));
			} catch (Exception e) {
				LOGGER.warning("There was an error!");
			}
		}
		return dataSum;
	}

	public List<NivoBarCouponDataByMonth> getNivoBarData(PidCouponsParameters parameters) {
		List<NivoBarCouponDataByMonth> dataList = getAllSumsRow(parameters);
		dataList.forEach(element -> {
			for (PersonType personType : PersonType.values()) {
				try {
					if (!Validators.isPersonTypeRequested(parameters.getPerson(), personType.value)) {
						String person = personType.methodValue;
						element.getClass().getMethod("set" + person, Long.class).invoke(element, 0L);
					}
				} catch (Exception e) {
					LOGGER.warning("There was an error!");
				}
			}
		});
		return dataList;
	}

	public List<NivoBarCouponDataByValidity> getNivoBarDataByValidity(PidCouponsParameters parameters) {
		return Validators.verifyValidityList(Collections.emptyList()).stream()
				.map(validity -> getBarDataFromRepository(validity, parameters))
				.collect(Collectors.toList());
	}

	public List<NivoBarMonthsDataByValidity> getMonthsByValidity(PidCouponsParameters parameters) {
		List<NivoBarMonthsDataByValidity> outputData = new ArrayList<>();
		for (String validity : parameters.getValidity()) {
			NivoBarMonthsDataByValidity outputObject = new NivoBarMonthsDataByValidity(validity);
			for (String month : parameters.getMonth()) {
				NivoBarCouponDataByMonth currentBarData = getAllSumsRow(parameters, Collections.singletonList(validity), null, Collections.singletonList(month), null).get(0);
				outputObject.setData(month, getDataSum(currentBarData, parameters.getPerson()));
			}
			outputData.add(outputObject);
		}
		return outputData;
	}

	public List<NivoBarValidityDataByMonth> getValidityByMonth(PidCouponsParameters parameters) {
		List<NivoBarValidityDataByMonth> outputList = new ArrayList<>();
		for (String month : parameters.getMonth()) {
			NivoBarValidityDataByMonth validityBarData = new NivoBarValidityDataByMonth(month);
			for (String validity : parameters.getValidity()) {
				NivoBarCouponDataByMonth monthData = getAllSumsRow(parameters, Collections.singletonList(validity), null, Collections.singletonList(month), null).get(0);
				validityBarData.setData(validity, getDataSum(monthData, parameters.getPerson()));
			}
			outputList.add(validityBarData);
		}
		return outputList;
	}

	private NivoBarCouponDataByValidity getBarDataFromRepository(String validity, PidCouponsParameters parameters) {
		NivoBarCouponDataByValidity outputData = new NivoBarCouponDataByValidity(validity);

		for (NivoBarCouponData element : getAllSumsRow(parameters, validity)) {
			for (PersonType personType : PersonType.values()) {
				try {
					if (Validators.isPersonTypeRequested(parameters.getPerson(), personType.value)) {
						String person = personType.methodValue;
						Long receivedValue = (Long) element.getClass().getMethod("get" + person).invoke(element);
						outputData.getClass().getMethod("addTo" + person, Long.class).invoke(outputData, receivedValue);
					}
				} catch (Exception e) {
					LOGGER.warning("There was an error!");
				}
			}
		}
		return outputData;
	}


	public List<NivoPieData> getNivoPieData(PidCouponsParameters parameters) {
		NivoBarCouponData pieData = pidCouponsRepository.getNivoPieData(parameters.getValidity(), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
		List<NivoPieData> outputData = new ArrayList<>();

		for (PersonType personType : PersonType.values()) {
			try {
				if (Validators.isPersonTypeRequested(parameters.getPerson(), personType.value)) {
					String person = personType.methodValue;
					Long receivedValue = (Long) pieData.getClass().getMethod("get" + person).invoke(pieData);

					NivoPieData newData = new NivoPieData(personType.value, receivedValue);
					outputData.add(newData);
				}
			} catch (Exception e) {
				LOGGER.warning("There was an error!");
			}
		}
		return outputData;
	}

	public List<NivoPieData> getNivoPieDataByValidity(PidCouponsParameters parameters) {
		List<NivoPieData> outputData = Validators.verifyValidityList(Collections.emptyList()).stream().map(NivoPieData::new).collect(Collectors.toList());
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
			NivoBarCouponData data = pidCouponsRepository.getNivoPieData(Collections.singletonList(couponType), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
			for (PersonType personType : PersonType.values()) {
				try {
					if (Validators.isPersonTypeRequested(parameters.getPerson(), personType.value)) {
						String person = personType.methodValue;
						Long receivedValue = (Long) data.getClass().getMethod("get" + person).invoke(data);
						children.addSecondChildren(personType.getValue(), receivedValue);
					}
				} catch (Exception e) {
					LOGGER.warning("There was an error!");
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

	private String findColumnByValue(String lookedPerson) {
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

	private List<PersonAbstractClass> createPersonList(NivoBarCouponDataByMonth data, List<String> personTypes) {
		List<PersonAbstractClass> personsList = new ArrayList<>();
		String month = data.getMonth();

		for (PersonType personType : PersonType.values()) {
			try {
				if (Validators.isPersonTypeRequested(personTypes, personType.value)) {
					String person = personType.methodValue;
					Long receivedValue = (Long) data.getClass().getMethod("get" + person).invoke(data);
					personsList.add(new PersonAbstractClass(personType.value, month, receivedValue));
				}
			} catch (Exception e) {
				LOGGER.warning("There was an error!");
			}
		}
		return personsList;
	}

	public List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters, String value) {
		return pidCouponsRepository.getNivoBarDataByValidity(value, parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
	}

	public List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters) {
		return pidCouponsRepository.getNivoBarData(parameters.getValidity(), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
	}

	private List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters, List<String> validity, List<String> sellType, List<String> month, List<Integer> year) {

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

		return pidCouponsRepository.getNivoBarData(validity, sellType, month, year);
	}
}
