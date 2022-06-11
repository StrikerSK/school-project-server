package com.charts.general.entity.ticket;

import com.charts.general.entity.AbstractParameters;
import com.charts.general.entity.enums.EnumTypes;
import com.charts.general.entity.enums.EnumUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TicketsParameters extends AbstractParameters {

	private final List<Boolean> discounted;
	private final List<String> ticketType;

	public TicketsParameters(List<String> month, List<Integer> year, List<Boolean> discounted, List<String> ticketType) {
		super(month, year);
		this.discounted = discounted;
		this.ticketType = ticketType;
	}

	public List<Boolean> getDiscounted() {
		if (CollectionUtils.isEmpty(ticketType)) {
			return Stream.of(true, false).collect(Collectors.toList());
		}

		return discounted;
	}

	public List<String> getTicketType() {
		List<String> ticketTypeList = EnumUtils.getStringValues(EnumTypes.TICKET_TYPE_ENUM);

		if (CollectionUtils.isEmpty(ticketType)) {
			return ticketTypeList;
		}

		return ticketType.stream().filter(ticketTypeList::contains).collect(Collectors.toList());
	}
}
