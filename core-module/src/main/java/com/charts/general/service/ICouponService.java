package com.charts.general.service;

import com.charts.general.entity.coupon.CouponsParameters;

import java.util.Map;

public interface ICouponService {

	Map<String, Integer> getAllData();
	Map<String, Integer> getMonthlyDataByValidity(CouponsParameters parameters);

}
