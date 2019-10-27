package com.javapid.entity;

import java.util.List;

import static com.javapid.service.Validators.verifyDiscountedList;
import static com.javapid.service.Validators.verifyTicketType;

public class PidTicketsParameters extends PidAbstractParameters {

	private List<Boolean> discounted;
	private List<String> ticketType;

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
