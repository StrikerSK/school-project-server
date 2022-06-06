package com.charts.general.service;

import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;

import java.util.List;
import java.util.Map;

public interface ICouponService {

	Map<String, Integer> getAllData();
	Map<String, Integer> getMonthlyDataByValidity(PidCouponsParameters parameters);
	List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters);

}
