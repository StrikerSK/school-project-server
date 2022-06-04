package com.charts.general.controller;

import com.charts.general.entity.coupon.updated.UpdateCouponEntity;
import com.charts.general.entity.coupon.updated.UpdateCouponList;
import com.charts.general.entity.nivo.bar.NivoBarMonthsDataByValidity;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.repository.coupon.JpaCouponRepository;
import com.charts.general.service.ICouponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.charts.general.constants.PersonType.PORTABLE_VALUE;

@RestController
@RequestMapping("/test")
public class ExperimentalController {
	private final ICouponService couponsService;
	private final JpaCouponRepository couponRepository;

	public ExperimentalController(ICouponService couponsService, JpaCouponRepository couponRepository) {
		this.couponsService = couponsService;
		this.couponRepository = couponRepository;
	}

	@RequestMapping("/validity/month")
	public List<NivoBarMonthsDataByValidity> getMonthDataByValidity(@RequestParam(required = false) List<String> validity,
	                                                                @RequestParam(required = false) List<String> type,
	                                                                @RequestParam(required = false) List<String> month,
	                                                                @RequestParam(required = false) List<String> year,
	                                                                @RequestParam(required = false) List<String> person) {
		return couponsService.getMonthsByValidity(new PidCouponsParameters(validity, type, month, year, person));
	}

	@GetMapping
	public Map<String, Integer> findAll() {
		List<UpdateCouponEntity> couponList = UpdateCouponList.NewCouponList(couponRepository.findAll());
		Map<String, Integer> customMap = new HashMap<>();

		couponList.stream().filter(e -> PORTABLE_VALUE.equals(e.getPersonType()))
				.forEach(e -> customMap.put(e.getMonth(), e.getValue()));

		return customMap;
	}

}
