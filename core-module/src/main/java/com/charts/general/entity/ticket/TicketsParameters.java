package com.charts.general.entity.ticket;

import com.charts.general.entity.PidAbstractParameters;

import java.util.List;

import static com.charts.general.utils.ParameterUtils.verifyDiscountedList;
import static com.charts.general.utils.ParameterUtils.verifyTicketType;

public class TicketsParameters extends PidAbstractParameters {

	private final List<Boolean> discounted;
	private final List<String> ticketType;

	public TicketsParameters(List<String> month, List<String> year, List<Boolean> discounted, List<String> ticketType) {
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
