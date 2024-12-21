package com.charts.general.entity.parameters;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.TicketTypes;
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
		if (CollectionUtils.isEmpty(discounted)) {
			return Stream.of(true, false).collect(Collectors.toList());
		}

		return discounted;
	}

	public List<TicketTypes> getTicketType() {
		if (CollectionUtils.isEmpty(ticketType)) {
			return EnumUtils.getValueList(TicketTypes.class);
		} else {
			return EnumUtils.convertStringsToEnums(ticketType, TicketTypes.class);
		}
	}
}
