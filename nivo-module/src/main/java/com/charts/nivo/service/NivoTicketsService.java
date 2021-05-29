package com.charts.nivo.service;

import com.charts.general.entity.PidTicketsParameters;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.nivo.NivoLineData;
import com.charts.general.entity.nivo.NivoPieData;
import com.charts.general.entity.nivo.TicketMainDAO;
import com.charts.general.entity.nivo.bar.NivoBarTicketsDAOByMonth;
import com.charts.general.repository.ticket.TicketQueryTemplates;
import com.charts.general.repository.ticket.TicketRepository;
import com.charts.general.service.Validators;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.charts.general.service.Validators.verifyTicketType;

@Service
public class NivoTicketsService {

	private final TicketRepository ticketRepository;
	private final TicketQueryTemplates ticketQueryTemplates;

	public NivoTicketsService(TicketRepository ticketRepository, TicketQueryTemplates ticketQueryTemplates) {
		this.ticketRepository = ticketRepository;
		this.ticketQueryTemplates = ticketQueryTemplates;
	}

	public List<NivoLineData> getTicketsLineData(PidTicketsParameters parameters) {
		return verifyTicketType(parameters.getTicketType()).stream()
				.map(e -> new NivoLineData(e, ticketQueryTemplates.getTicketLineData(TicketTypes.getTicketColumn(e), parameters)))
				.collect(Collectors.toList());
	}

	public List<NivoBarTicketsDAOByMonth> getTicketBarData(PidTicketsParameters parameters) {
		return ticketRepository.getTicketsBarData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger());
	}

	public List<NivoPieData> getTicketsPieData(PidTicketsParameters parameters) {
		TicketMainDAO pieData = ticketRepository.getTicketsPieData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger());
		return TicketTypes.getList().stream()
				.filter(e -> isTicketTypeRequested(parameters.getTicketType(), e.getValue()))
				.map(e -> new NivoPieData(e.getValue(), generatePieData(e, pieData)))
				.collect(Collectors.toList());
	}

	@SneakyThrows
	private Long generatePieData(TicketTypes ticketType, TicketMainDAO data){
		return (Long) data.getClass().getMethod("get" + ticketType.getMethodName()).invoke(data);
	}

	private static Boolean isTicketTypeRequested(List<String> ticketList, String personType) {
		return Validators.isEmptyList(ticketList, TicketTypes.ticketTypeValues()).contains(personType);
	}
}
