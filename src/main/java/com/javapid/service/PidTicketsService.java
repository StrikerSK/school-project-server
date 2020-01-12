package com.javapid.service;

import com.javapid.entity.PidTicketsParameters;
import com.javapid.entity.enums.TicketTypes;
import com.javapid.entity.nivo.DataAbstractJizdenky;
import com.javapid.entity.nivo.NivoJizdenkyBarData;
import com.javapid.entity.nivo.line.NivoGeneralLineData;
import com.javapid.entity.nivo.line.NivoLineAbstractData;
import com.javapid.entity.nivo.pie.NivoGeneralPieData;
import com.javapid.entity.nivo.pie.NivoPieAbstractData;
import com.javapid.repository.PidTicketsRepository;
import com.javapid.repository.JdbcTicketRepository;
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

	public List<NivoLineAbstractData> getTicketsLineData(PidTicketsParameters parameters) {
		return verifyTicketType(parameters.getTicketType()).stream()
				.map(element -> new NivoGeneralLineData(element, jdbcTicketRepository.getTicketLineData(getColumnName(element, TicketTypes.values()), parameters)))
				.collect(Collectors.toList());
	}

	public List<NivoJizdenkyBarData> getTicketBarData(PidTicketsParameters parameters) {
		return pidTicketsRepository.getTicketsBarData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger());
	}

	public List<NivoPieAbstractData> getTicketsPieData(PidTicketsParameters parameters) {
		DataAbstractJizdenky pieData = pidTicketsRepository.getTicketsPieData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger());
		List<NivoPieAbstractData> outputData = new ArrayList<>();
		List<String> ticketTypes = parameters.getTicketType();

		for(TicketTypes ticketType : TicketTypes.values()){
			try {
				if(isTicketTypeRequested(ticketTypes, ticketType.value)){
					Long returnedValue = (Long) pieData.getClass().getMethod("get" + ticketType.methodName).invoke(pieData);
					outputData.add(new NivoGeneralPieData(ticketType.value, returnedValue));
				}
			} catch (Exception e){
				LOGGER.warning("There was an error!");
			}

		}
		return outputData;
	}
}
