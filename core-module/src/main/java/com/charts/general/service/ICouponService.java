package com.charts.general.service;

import com.charts.general.entity.coupon.CouponEntity;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.nivo.bar.NivoBarCouponData;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;

import java.util.List;
import java.util.Map;

public interface ICouponService {

	Map<String, Integer> getAllData();
	List<CouponEntity> getDataByCode(String code);
	Long getDataSum(NivoBarCouponData element, List<String> personTypes);
	Map<String, Integer> getMonthlyDataByValidity(PidCouponsParameters parameters);
	List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters, String value);
	List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters);
	List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters, List<String> validity, List<String> sellType, List<String> month, List<Integer> year);

}
