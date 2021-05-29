package com.charts.recharts.service;

import com.charts.general.entity.PidTicketsParameters;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.nivo.bar.NivoBarTicketsDAOByMonth;
import com.charts.recharts.entity.PersonAbstractClass;
import com.charts.general.repository.ticket.TicketRepository;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

import static com.charts.general.service.Validators.isPersonTypeRequested;

public class RechartsTicketService {

	private final TicketRepository ticketsRepository;

	public RechartsTicketService(TicketRepository ticketsRepository){
		this.ticketsRepository = ticketsRepository;
	}

	public List<List<PersonAbstractClass>> getRechartsTicketsData(PidTicketsParameters parameters) {
		return ticketsRepository.getTicketsBarData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger()).stream()
				.map(element -> createTicketsList(element, parameters.getTicketType()))
				.collect(Collectors.toList());
	}

	private List<PersonAbstractClass> createTicketsList(NivoBarTicketsDAOByMonth data, List<String> ticketTypes) {
		return TicketTypes.getList().stream()
				.filter(e -> isPersonTypeRequested(ticketTypes, e.getValue()))
				.map(e -> new PersonAbstractClass(e.getValue(), data.getMonth(), generateValue(e, data)))
				.collect(Collectors.toList());
	}

	@SneakyThrows
	private Long generateValue(TicketTypes ticketType, NivoBarTicketsDAOByMonth data){
		return (Long) data.getClass().getMethod("get" + ticketType.getMethodName()).invoke(data);
	}

}
