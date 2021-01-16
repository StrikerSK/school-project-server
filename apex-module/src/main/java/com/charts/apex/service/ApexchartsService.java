package com.charts.apex.service;

import com.charts.apex.entity.ApexchartsObject;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.PidTicketsParameters;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.repository.JdbcCouponRepository;
import com.charts.general.repository.JdbcTicketRepository;
import com.charts.general.service.PidCouponsService;
import com.charts.general.service.ServiceAbstract;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApexchartsService extends ServiceAbstract {

	private final JdbcCouponRepository jdbcCouponRepository;
	private final JdbcTicketRepository jdbcTicketRepository;
	private final PidCouponsService pidCouponsService;

	public ApexchartsService(JdbcCouponRepository jdbcCouponRepository, JdbcTicketRepository jdbcTicketRepository, PidCouponsService pidCouponsService) {
		this.jdbcCouponRepository = jdbcCouponRepository;
		this.jdbcTicketRepository = jdbcTicketRepository;
		this.pidCouponsService = pidCouponsService;
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

	/**
	 * Method retrieves Apex data by validity
	 */
	public List<ApexchartsObject> getApexDataByValidity(PidCouponsParameters parameters) {
		return parameters.getValidity().stream()
				.map(e -> getValues(e, parameters))
				.collect(Collectors.toList());
	}

	private ApexchartsObject getValues(String validity, PidCouponsParameters parameters) {
		List<Long> dataSum = pidCouponsService.getAllSumsRow(parameters, validity).stream()
				.map(e -> pidCouponsService.getDataSum(e, parameters.getPerson()))
				.collect(Collectors.toList());
		return new ApexchartsObject(validity, dataSum);
	}
}
