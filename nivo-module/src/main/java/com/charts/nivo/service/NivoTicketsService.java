package com.charts.nivo.service;

import com.charts.general.ClassMethodInvoker;
import com.charts.general.entity.ticket.TicketsParameters;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import com.charts.general.entity.nivo.TicketMainDAO;
import com.charts.general.entity.nivo.bar.NivoBarTicketsDAOByMonth;
import com.charts.general.repository.ticket.TicketQueryTemplates;
import com.charts.general.repository.ticket.TicketRepository;
import com.charts.general.utils.ParameterUtils;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.charts.general.utils.ParameterUtils.verifyTicketType;

@Service
public class NivoTicketsService {

	private final TicketRepository ticketRepository;
	private final TicketQueryTemplates ticketQueryTemplates;

	public NivoTicketsService(TicketRepository ticketRepository, TicketQueryTemplates ticketQueryTemplates) {
		this.ticketRepository = ticketRepository;
		this.ticketQueryTemplates = ticketQueryTemplates;
	}

	public List<NivoLineData> getTicketsLineData(TicketsParameters parameters) {
		return verifyTicketType(parameters.getTicketType()).stream()
				.map(e -> new NivoLineData(e, ticketQueryTemplates.getTicketLineData(TicketTypes.getTicketColumn(e), parameters)))
				.collect(Collectors.toList());
	}

	public List<NivoBarTicketsDAOByMonth> getTicketBarData(TicketsParameters parameters) {
		return ticketRepository.getTicketsBarData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger());
	}

	public List<NivoPieData> getTicketsPieData(TicketsParameters parameters) {
		TicketMainDAO pieData = ticketRepository.getTicketsPieData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger());
		return TicketTypes.getList().stream()
				.filter(e -> isTicketTypeRequested(parameters.getTicketType(), e.getValue()))
				.map(e -> new NivoPieData(e.getValue(), generatePieData(e, pieData)))
				.collect(Collectors.toList());
	}

	@SneakyThrows
	private Long generatePieData(TicketTypes ticketType, TicketMainDAO data){
		return (Long) ClassMethodInvoker.invokeClassGetMethod(data, ticketType.getMethodName());
	}

	private static Boolean isTicketTypeRequested(List<String> ticketList, String personType) {
		return ParameterUtils.isEmptyList(ticketList, TicketTypes.ticketTypeValues()).contains(personType);
	}
}
