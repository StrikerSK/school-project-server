package com.javapid.service;

import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.ApexchartsData;
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

	public List<ApexchartsData> getApexData(final PidCouponsParameters parameters) {
		return Arrays.stream(PersonType.values())
				.map(e -> new ApexchartsData(e.value, jdbcCouponRepository.fetchCouponAreaData(e.column, parameters)))
				.collect(Collectors.toList());
	}
}
