package com.charts.general.entity;

import java.util.List;

import static com.charts.general.service.Validators.verifyDiscountedList;
import static com.charts.general.service.Validators.verifyTicketType;

public class PidTicketsParameters extends PidAbstractParameters {

	private final List<Boolean> discounted;
	private final List<String> ticketType;

	public PidTicketsParameters(List<String> month, List<String> year, List<Boolean> discounted, List<String> ticketType) {
		super(month, year);
		this.discounted = discounted;
		this.ticketType = ticketType;
	}

	public List<Boolean> getDiscounted() {
		return verifyDiscountedList(discounted);
	}

	public List<String> getTicketType() {
		return verifyTicketType(ticketType);
	}
}
