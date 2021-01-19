package com.charts.nivo.service;

import com.charts.general.entity.PidTicketsParameters;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.nivo.NivoLineData;
import com.charts.general.entity.nivo.NivoPieData;
import com.charts.general.entity.nivo.TicketMainDAO;
import com.charts.general.entity.nivo.bar.NivoBarTicketsDAOByMonth;
import com.charts.general.repository.JdbcTicketRepository;
import com.charts.general.repository.PidTicketsRepository;
import com.charts.general.service.ServiceAbstract;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.charts.general.service.Validators.*;

@Service
public class NivoTicketsService extends ServiceAbstract {

	private final PidTicketsRepository pidTicketsRepository;
	private final JdbcTicketRepository jdbcTicketRepository;
	private static final Logger LOGGER = Logger.getLogger("Ticket service");

	public NivoTicketsService(PidTicketsRepository pidTicketsRepository, JdbcTicketRepository jdbcTicketRepository) {
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
}
