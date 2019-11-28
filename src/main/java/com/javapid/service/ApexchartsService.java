package com.javapid.service;

import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.apexcharts.ApexchartsAreaData;
import com.javapid.entity.enums.PersonType;
import com.javapid.repository.JdbcCouponRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApexchartsService {

	private final JdbcCouponRepository jdbcCouponRepository;

	public ApexchartsService(JdbcCouponRepository jdbcCouponRepository) {
		this.jdbcCouponRepository = jdbcCouponRepository;
	}

	public List<ApexchartsAreaData> getAreaData(final PidCouponsParameters parameters) {
		return Arrays.stream(PersonType.values())
				.map(e -> new ApexchartsAreaData(e.value, jdbcCouponRepository.fetchCouponAreaData(e.column, parameters)))
				.collect(Collectors.toList());
	}
}
