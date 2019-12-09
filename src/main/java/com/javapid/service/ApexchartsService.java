package com.javapid.service;

import com.javapid.entity.ApexchartsData;
import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.enums.PersonType;
import com.javapid.repository.JdbcCouponRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApexchartsService extends ServiceAbstract {

	private final JdbcCouponRepository jdbcCouponRepository;

	public ApexchartsService(JdbcCouponRepository jdbcCouponRepository) {
		this.jdbcCouponRepository = jdbcCouponRepository;
	}

	public List<ApexchartsData> getApexData(final PidCouponsParameters parameters) {
		return parameters.getPerson().stream()
				.map(e -> new ApexchartsData(e, jdbcCouponRepository.fetchCouponAreaData(getColumnName(e, PersonType.values()), parameters)))
				.collect(Collectors.toList());
	}
}
