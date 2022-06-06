package com.charts.nivo.service;

import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.coupon.updated.UpdateCouponEntity;
import com.charts.general.entity.coupon.updated.UpdateCouponList;
import com.charts.general.entity.enums.Validity;
import com.charts.general.entity.nivo.DataXY;
import com.charts.general.entity.nivo.NivoLineData;
import com.charts.general.entity.nivo.NivoPieData;
import com.charts.general.entity.nivo.BubbleData;
import com.charts.general.repository.coupon.NewCouponRepository;
import com.charts.general.utils.GroupingUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NivoCouponService {

	private final NewCouponRepository couponRepository;

	public NivoCouponService(NewCouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}

	public List<NivoLineData> getNivoLineData(PidCouponsParameters parameters) {
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<NivoLineData> output = new ArrayList<>();
		GroupingUtils.groupByMonth(couponList.getCouponEntityList())
				.forEach((month, entity) ->  {
					List<DataXY> nestedData = new ArrayList<>();
					GroupingUtils.groupByAndSumByPerson(entity)
							.forEach((validity, integer) -> nestedData.add(new DataXY(validity, ((Integer) integer).longValue())));
					output.add(new NivoLineData(month, nestedData));
				});
		return output;
	}

	/**
	 * Method gets line data by validity
	 *
	 * @param parameters all requested parameters
	 * @return data for displaying line chart by validity
	 */
	public List<NivoLineData> getValidityDataByMonth(PidCouponsParameters parameters) {
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<NivoLineData> output = new ArrayList<>();
		GroupingUtils.groupByMonth(couponList.getCouponEntityList())
				.forEach((month, entity) ->  {
					List<DataXY> nestedData = new ArrayList<>();
					GroupingUtils.groupByAndSumByValidity(entity)
							.forEach((validity, integer) -> nestedData.add(new DataXY(validity, ((Integer) integer).longValue())));
					output.add(new NivoLineData(month, nestedData));
				});

		return output;
	}

	public List<NivoPieData> getValidityData(PidCouponsParameters parameters) {
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<NivoPieData> pieData = new ArrayList<>();
		GroupingUtils.groupByAndSumByValidity(couponList.getCouponEntityList())
				.forEach((validity, total) -> pieData.add(new NivoPieData(validity, ((Integer) total).longValue())));
		return pieData;
	}

	public List<Map<String, Object>> getPersonDataByMonthBar(PidCouponsParameters parameters) {
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<Map<String, Object>> outputMapList = new ArrayList<>();
		GroupingUtils.groupByMonth(couponList.getCouponEntityList())
				.forEach((month, entities) ->  {
					Map<String, Object> tmpMap = new HashMap<>(GroupingUtils.groupByAndSumByPerson(entities));
					tmpMap.put("month", month);
					outputMapList.add(tmpMap);
				});
		return outputMapList;
	}

	public BubbleData getBubbleChartData(PidCouponsParameters parameters) {
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<BubbleData> middleBubbleDataList = new ArrayList<>();
		GroupingUtils.groupByPersonType(couponList.getCouponEntityList()).forEach((key, entity) -> {
			List<BubbleData> nestedBubbleDataList = new ArrayList<>();
			GroupingUtils.groupByAndSumByValidity(entity)
					.forEach((validity, integer) -> nestedBubbleDataList.add(new BubbleData(validity, (Integer) integer)));
			middleBubbleDataList.add(new BubbleData(key.getValue(), nestedBubbleDataList));
		});
		return new BubbleData("Predaj kupónov", middleBubbleDataList);
	}

	public Map<String, Map<Validity, Integer>> getValidityByMonth(PidCouponsParameters parameters) {
		Map<String, Map<Validity, Integer>> outputMap = new HashMap<>();
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		GroupingUtils.groupByMonth(couponList.getCouponEntityList()).forEach((key, entity) -> {
			Map<Validity, Integer> validityMap = new HashMap(entity.stream()
					.collect(Collectors.groupingBy(UpdateCouponEntity::getValidity, Collectors.summingInt(UpdateCouponEntity::getValue))));
			outputMap.put(key, validityMap);
		});
		return outputMap;
	}

	public List<Map<String, Object>> getNivoBarDataByValidity(PidCouponsParameters parameters) {
		List<Map<String, Object>> output = new ArrayList<>();
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		GroupingUtils.groupByMonth(couponList.getCouponEntityList()).forEach((key, entity) -> {
			Map<String, Object> tmpMap = new HashMap<>(GroupingUtils.groupByAndSumByValidity(entity));
			tmpMap.put("month", key);
			output.add(tmpMap);
		});
		return output;
	}

	public List<Map<String, Object>> getYearlyDataByPersonType(PidCouponsParameters parameters) {
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<Map<String, Object>> mapList = new ArrayList<>();
		GroupingUtils.groupByYear(couponList.getCouponEntityList())
				.forEach((year, entity) -> {
					Map<String, Object> tmpMap = GroupingUtils.groupByAndSumByPerson(entity);
					tmpMap.put("year", year);
					mapList.add(tmpMap);
				});
		return mapList;
	}

	public List<Map<String, Object>> getYearlyDataByMonth(PidCouponsParameters parameters) {
		UpdateCouponList couponList = couponRepository.getUpdateCouponList().filterWithParameters(parameters);
		List<Map<String, Object>> mapList = new ArrayList<>();
		GroupingUtils.groupByYear(couponList.getCouponEntityList())
				.forEach((year, entity) -> {
					Map<String, Object> tmpMap = GroupingUtils.groupByAndSumByMonth(entity);
					tmpMap.put("year", year);
					mapList.add(tmpMap);
				});
		return mapList;
	}

}
