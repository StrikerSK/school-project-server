package com.charts.nivo.service;

import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.nivo.DataXY;
import com.charts.general.entity.nivo.NivoLineData;
import com.charts.general.entity.nivo.NivoPieData;
import com.charts.general.entity.nivo.bar.NivoBarCouponData;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByValidity;
import com.charts.general.entity.nivo.bar.NivoBarValidityDataByMonth;
import com.charts.general.entity.nivo.bubble.BubbleChartData;
import com.charts.general.entity.nivo.bubble.NivoBubbleAbstract;
import com.charts.general.entity.nivo.bubble.NivoBubbleData;
import com.charts.general.repository.coupon.CouponQueryTemplate;
import com.charts.general.repository.coupon.CouponRepository;
import com.charts.general.service.ICouponService;
import com.charts.general.service.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NivoCouponService {

	private final CouponQueryTemplate couponQueryTemplate;
	private final ICouponService couponService;
	private final CouponRepository couponRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(NivoCouponService.class);

	public NivoCouponService(CouponQueryTemplate couponQueryTemplate, ICouponService couponService, CouponRepository couponRepository) {
		this.couponQueryTemplate = couponQueryTemplate;
		this.couponService = couponService;
		this.couponRepository = couponRepository;
	}

	public List<NivoLineData> getNivoLineData(PidCouponsParameters parameters) {
		return Validators.verifyPersonList(parameters.getPerson()).stream()
				.map(element -> new NivoLineData(element, couponQueryTemplate.getLineData(findColumnByValue(element), parameters)))
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
			List<DataXY> outputBarData = couponService.getAllSumsRow(parameters, validity).stream()
					.map(element -> new DataXY(element.getMonth(), couponService.getDataSum(element, parameters.getPerson())))
					.collect(Collectors.toList());
			singleElement.setData(outputBarData);
			outputValidityData.add(singleElement);
		}
		return outputValidityData;
	}

	public List<NivoPieData> getNivoPieDataByValidity(PidCouponsParameters parameters) {
		List<NivoPieData> outputData = Validators.verifyValidityList(Collections.emptyList()).stream().map(NivoPieData::new).collect(Collectors.toList());
		outputData.forEach(element -> element.setValue(setPieValidityValue(element.getId(), parameters)));
		return outputData;
	}

	private Long setPieValidityValue(String validity, PidCouponsParameters parameters) {
		return couponService.getAllSumsRow(parameters, validity).stream()
				.mapToLong(element -> couponService.getDataSum(element, parameters.getPerson()))
				.sum();
	}

	public List<NivoBarCouponDataByMonth> getNivoBarData(PidCouponsParameters parameters) {
		List<NivoBarCouponDataByMonth> dataList = couponService.getAllSumsRow(parameters);
		dataList.forEach(element -> {
			for (PersonType personType : PersonType.values()) {
				try {
					if (!Validators.isPersonTypeRequested(parameters.getPerson(), personType.value)) {
						String person = personType.methodValue;
						element.getClass().getMethod("set" + person, Long.class).invoke(element, 0L);
					}
				} catch (Exception e) {
					LOGGER.error("There was an error!", e);
				}
			}
		});
		return dataList;
	}

	public BubbleChartData getNivoBubbleChartByValidity(PidCouponsParameters parameters) {
		BubbleChartData outputData = new BubbleChartData("Predaj kupónov");
		for (String couponType : parameters.getValidity()) {
			BubbleChartData.FirstChildren children = new BubbleChartData.FirstChildren(couponType);
			NivoBarCouponData data = couponRepository.getNivoPieData(Collections.singletonList(couponType), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
			for (PersonType personType : PersonType.values()) {
				try {
					if (Validators.isPersonTypeRequested(parameters.getPerson(), personType.value)) {
						String person = personType.methodValue;
						Long receivedValue = (Long) data.getClass().getMethod("get" + person).invoke(data);
						children.addSecondChildren(personType.getValue(), receivedValue);
					}
				} catch (Exception e) {
					LOGGER.error("There was an error!", e);
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
					Long sum = couponQueryTemplate.getBubbleData(findColumnByValue(person), validity, month, parameters);
					secondComplexChildren.addToList(validity, sum);
				}
				firstComplexChildren.addChildren(secondComplexChildren);
			}
			outputData.addChildren(firstComplexChildren);
		}
		return outputData;
	}

	public BubbleChartData getNivoBubbleChart(PidCouponsParameters parameters) {
		BubbleChartData outputData = new BubbleChartData("Predaj kupónov");
		for (String personType : parameters.getPerson()) {
			BubbleChartData.FirstChildren firstChildren = new BubbleChartData.FirstChildren(personType);
			String personColumn = findColumnByValue(personType);
			for (String couponType : parameters.getValidity()) {
				firstChildren.addSecondChildren(couponType, couponQueryTemplate.getBubbleData(personColumn, couponType, parameters));
			}
			outputData.addFirstChildren(firstChildren);
		}
		return outputData;
	}

	public String findColumnByValue(String lookedPerson) {
		for (PersonType personType : PersonType.values()) {
			if (personType.getValue().equals(lookedPerson)) {
				return personType.getColumn();
			}
		}
		return null;
	}

	public List<NivoBarValidityDataByMonth> getValidityByMonth(PidCouponsParameters parameters) {
		List<NivoBarValidityDataByMonth> outputList = new ArrayList<>();
		for (String month : parameters.getMonth()) {
			NivoBarValidityDataByMonth validityBarData = new NivoBarValidityDataByMonth(month);
			for (String validity : parameters.getValidity()) {
				NivoBarCouponDataByMonth monthData = couponService.getAllSumsRow(parameters, Collections.singletonList(validity), null, Collections.singletonList(month), null).get(0);
				validityBarData.setData(validity,  couponService.getDataSum(monthData, parameters.getPerson()));
			}
			outputList.add(validityBarData);
		}
		return outputList;
	}

	public List<NivoBarCouponDataByValidity> getNivoBarDataByValidity(PidCouponsParameters parameters) {
		return Validators.verifyValidityList(Collections.emptyList()).stream()
				.map(validity -> getBarDataFromRepository(validity, parameters))
				.collect(Collectors.toList());
	}

	private NivoBarCouponDataByValidity getBarDataFromRepository(String validity, PidCouponsParameters parameters) {
		NivoBarCouponDataByValidity outputData = new NivoBarCouponDataByValidity(validity);

		for (NivoBarCouponData element : couponService.getAllSumsRow(parameters, validity)) {
			for (PersonType personType : PersonType.values()) {
				try {
					if (Validators.isPersonTypeRequested(parameters.getPerson(), personType.value)) {
						String person = personType.methodValue;
						Long receivedValue = (Long) element.getClass().getMethod("get" + person).invoke(element);
						outputData.getClass().getMethod("addTo" + person, Long.class).invoke(outputData, receivedValue);
					}
				} catch (Exception e) {
					LOGGER.error("There was an error!", e);
				}
			}
		}
		return outputData;
	}

	public List<NivoPieData> getNivoPieData(PidCouponsParameters parameters) {
		NivoBarCouponData pieData = couponRepository.getNivoPieData(parameters.getValidity(), parameters.getSellType(), parameters.getMonth(), parameters.getYearInteger());
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
				LOGGER.error("There was an error!", e);
			}
		}
		return outputData;
	}
}
