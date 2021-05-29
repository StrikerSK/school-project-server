package com.charts.apex.service;

import com.charts.apex.entity.ApexchartsObject;
import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.PidTicketsParameters;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.repository.coupon.CouponQueryTemplate;
import com.charts.general.repository.ticket.TicketQueryTemplates;
import com.charts.general.service.ICouponService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApexchartsService {

	private final CouponQueryTemplate couponQueryTemplate;
	private final TicketQueryTemplates ticketQueryTemplates;
	private final ICouponService couponService;

	public ApexchartsService(CouponQueryTemplate couponQueryTemplate, TicketQueryTemplates ticketQueryTemplates, ICouponService couponService) {
		this.couponQueryTemplate = couponQueryTemplate;
		this.ticketQueryTemplates = ticketQueryTemplates;
		this.couponService = couponService;
	}

	public List<ApexchartsObject> getApexData(final PidCouponsParameters parameters) {
		return parameters.getPerson().stream()
				.map(e -> new ApexchartsObject(e, couponQueryTemplate.getAreaData(PersonType.getPersonColumn(e), parameters)))
				.collect(Collectors.toList());
	}

	public List<ApexchartsObject> getApexTicketData(final PidTicketsParameters parameters) {
		return parameters.getTicketType().stream()
				.map(e -> new ApexchartsObject(e, ticketQueryTemplates.getApexTicketLongData(TicketTypes.getTicketColumn(e), parameters)))
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
		List<Long> dataSum = couponService.getAllSumsRow(parameters, validity).stream()
				.map(e -> couponService.getDataSum(e, parameters.getPerson()))
				.collect(Collectors.toList());
		return new ApexchartsObject(validity, dataSum);
	}
}
