package com.javapid.service;

import com.javapid.entity.PidTicketsParameters;
import com.javapid.entity.enums.TicketTypes;
import com.javapid.entity.nivo.NivoLineData;
import com.javapid.entity.nivo.NivoPieData;
import com.javapid.entity.nivo.TicketMainDAO;
import com.javapid.entity.nivo.bar.NivoBarTicketsDAOByMonth;
import com.javapid.objects.recharts.PersonAbstractClass;
import com.javapid.repository.JdbcTicketRepository;
import com.javapid.repository.PidTicketsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.javapid.service.Validators.*;

@Service
public class PidTicketsService extends ServiceAbstract {

	private final PidTicketsRepository pidTicketsRepository;
	private final JdbcTicketRepository jdbcTicketRepository;
	private static final Logger LOGGER = Logger.getLogger("Ticket service");

	public PidTicketsService(PidTicketsRepository pidTicketsRepository, JdbcTicketRepository jdbcTicketRepository) {
		this.pidTicketsRepository = pidTicketsRepository;
		this.jdbcTicketRepository = jdbcTicketRepository;
	}

	public List<NivoLineData> getTicketsLineData(PidTicketsParameters parameters) {
		return verifyTicketType(parameters.getTicketType()).stream()
				.map(element -> new NivoLineData(element, jdbcTicketRepository.getTicketLineData(getColumnName(element, TicketTypes.values()), parameters)))
				.collect(Collectors.toList());
	}

	public List<NivoBarTicketsDAOByMonth> getTicketBarData(PidTicketsParameters parameters) {
		return pidTicketsRepository.getTicketsBarData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger());
	}

	public List<NivoPieData> getTicketsPieData(PidTicketsParameters parameters) {
		TicketMainDAO pieData = pidTicketsRepository.getTicketsPieData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger());
		List<NivoPieData> outputData = new ArrayList<>();
		List<String> ticketTypes = parameters.getTicketType();

		for (TicketTypes ticketType : TicketTypes.values()) {
			try {
				if (isTicketTypeRequested(ticketTypes, ticketType.value)) {
					Long returnedValue = (Long) pieData.getClass().getMethod("get" + ticketType.methodName).invoke(pieData);
					outputData.add(new NivoPieData(ticketType.value, returnedValue));
				}
			} catch (Exception e) {
				LOGGER.warning("There was an error!");
			}
		}
		return outputData;
	}

	public List<List<PersonAbstractClass>> getRechartsTicketsData(PidTicketsParameters parameters) {
		return pidTicketsRepository.getTicketsBarData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger()).stream()
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
				LOGGER.warning("There was an error!");
			}
		}
		return ticketList;
	}
}
