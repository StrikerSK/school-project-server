package com.charts.general.service;

import com.charts.general.entity.CouponEntity;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.nivo.bar.NivoBarCouponData;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;
import com.charts.general.entity.nivo.bar.NivoBarMonthsDataByValidity;

import java.util.List;

public interface ICouponService {

	List<CouponEntity> getAllData();
	List<CouponEntity> getDataByCode(String code);
	Long getDataSum(NivoBarCouponData element, List<String> personTypes);
	List<NivoBarMonthsDataByValidity> getMonthsByValidity(PidCouponsParameters parameters);
	List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters, String value);
	List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters);
	List<NivoBarCouponDataByMonth> getAllSumsRow(final PidCouponsParameters parameters, List<String> validity, List<String> sellType, List<String> month, List<Integer> year);

}
