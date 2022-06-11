package com.charts.general.entity.ticket;

import com.charts.general.entity.AbstractParameters;

import java.util.List;

import static com.charts.general.utils.ParameterUtils.verifyDiscountedList;
import static com.charts.general.utils.ParameterUtils.verifyTicketType;

public class TicketsParameters extends AbstractParameters {

	private final List<Boolean> discounted;
	private final List<String> ticketType;

	public TicketsParameters(List<String> month, List<Integer> year, List<Boolean> discounted, List<String> ticketType) {
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
