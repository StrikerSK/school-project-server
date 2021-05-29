package com.charts.recharts.service;

import com.charts.general.entity.PidTicketsParameters;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.nivo.bar.NivoBarTicketsDAOByMonth;
import com.charts.recharts.entity.PersonAbstractClass;
import com.charts.general.repository.ticket.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.charts.general.service.Validators.isPersonTypeRequested;

public class RechartsTicketService {

	private final TicketRepository ticketsRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(RechartsTicketService.class);

	public RechartsTicketService(TicketRepository ticketsRepository){
		this.ticketsRepository = ticketsRepository;
	}

	public List<List<PersonAbstractClass>> getRechartsTicketsData(PidTicketsParameters parameters) {
		return ticketsRepository.getTicketsBarData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger()).stream()
				.map(element -> createTicketsList(element, parameters.getTicketType()))
				.collect(Collectors.toList());
	}

	private List<PersonAbstractClass> createTicketsList(NivoBarTicketsDAOByMonth data, List<String> ticketTypes) {
		List<PersonAbstractClass> ticketList = new ArrayList<>();
		String month = data.getMonth();

		for (TicketTypes ticketType : TicketTypes.values()) {
			try {
				if (isPersonTypeRequested(ticketTypes, ticketType.value)) {
					String ticket = ticketType.methodName;
					Long receivedValue = (Long) data.getClass().getMethod("get" + ticket).invoke(data);
					ticketList.add(new PersonAbstractClass(ticketType.value, month, receivedValue));
				}
			} catch (Exception e) {
				LOGGER.error("There was an error!", e);
			}
		}
		return ticketList;
	}

}
