package com.javapid.service;

import com.javapid.entity.ApexchartsObject;
import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.PidTicketsParameters;
import com.javapid.entity.enums.PersonType;
import com.javapid.entity.enums.TicketTypes;
import com.javapid.repository.JdbcCouponRepository;
import com.javapid.repository.JdbcTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApexchartsService extends ServiceAbstract {

	private final JdbcCouponRepository jdbcCouponRepository;
	private final JdbcTicketRepository jdbcTicketRepository;

	public ApexchartsService(JdbcCouponRepository jdbcCouponRepository, JdbcTicketRepository jdbcTicketRepository) {
		this.jdbcCouponRepository = jdbcCouponRepository;
		this.jdbcTicketRepository = jdbcTicketRepository;
	}

	public List<ApexchartsObject> getApexData(final PidCouponsParameters parameters) {
		return parameters.getPerson().stream()
				.map(e -> new ApexchartsObject(e, jdbcCouponRepository.fetchCouponAreaData(getColumnName(e, PersonType.values()), parameters)))
				.collect(Collectors.toList());
	}

	public List<ApexchartsObject> getApexTicketData(final PidTicketsParameters parameters) {
		return parameters.getTicketType().stream()
				.map(e -> new ApexchartsObject(e, jdbcTicketRepository.getApexTicketLongData(getColumnName(e, TicketTypes.values()), parameters)))
				.collect(Collectors.toList());
	}

	public List<ApexchartsData> getApexTicketData(final PidTicketsParameters parameters) {
		return parameters.getTicketType().stream()
				.map(e -> new ApexchartsData(e, jdbcTicketRepository.getApexTicketLongData(getColumnName(e, TicketTypes.values()), parameters)))
				.collect(Collectors.toList());
	}
}
