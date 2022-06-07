package com.charts.apex.service;

import com.charts.apex.entity.ApexObject;
import com.charts.general.entity.ticket.TicketsParameters;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.repository.ticket.TicketQueryTemplates;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApexTicketService {

	private final TicketQueryTemplates ticketQueryTemplates;

	public List<ApexObject> getApexTicketData(final TicketsParameters parameters) {
		return parameters.getTicketType().stream()
				.map(e -> new ApexObject(e, ticketQueryTemplates.getApexTicketLongData(TicketTypes.getTicketColumn(e), parameters)))
				.collect(Collectors.toList());
	}

}
