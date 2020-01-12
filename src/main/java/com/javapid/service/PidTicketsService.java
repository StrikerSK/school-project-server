package com.javapid.service;

import com.javapid.entity.PidTicketsParameters;
import com.javapid.entity.enums.TicketTypes;
import com.javapid.entity.nivo.DataAbstractJizdenky;
import com.javapid.entity.nivo.NivoBarJizdenkyDAO;
import com.javapid.entity.nivo.line.NivoLineCouponDAO;
import com.javapid.entity.nivo.pie.NivoPieCouponDAOExtended;
import com.javapid.entity.nivo.pie.NivoPieCouponDAO;
import com.javapid.repository.JdbcTicketRepository;
import com.javapid.repository.PidTicketsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.javapid.service.Validators.isTicketTypeRequested;
import static com.javapid.service.Validators.verifyTicketType;

@Service
public class PidTicketsService extends ServiceAbstract {

	private final PidTicketsRepository pidTicketsRepository;
	private final JdbcTicketRepository jdbcTicketRepository;
	private static final Logger LOGGER = Logger.getLogger("Ticket service");

	public PidTicketsService(PidTicketsRepository pidTicketsRepository, JdbcTicketRepository jdbcTicketRepository) {
		this.pidTicketsRepository = pidTicketsRepository;
		this.jdbcTicketRepository = jdbcTicketRepository;
	}

	public List<NivoLineCouponDAO> getTicketsLineData(PidTicketsParameters parameters) {
		return verifyTicketType(parameters.getTicketType()).stream()
				.map(element -> new NivoLineCouponDAO(element, jdbcTicketRepository.getTicketLineData(getColumnName(element, TicketTypes.values()), parameters)))
				.collect(Collectors.toList());
	}

	public List<NivoBarJizdenkyDAO> getTicketBarData(PidTicketsParameters parameters) {
		return pidTicketsRepository.getTicketsBarData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger());
	}

	public List<NivoPieCouponDAO> getTicketsPieData(PidTicketsParameters parameters) {
		DataAbstractJizdenky pieData = pidTicketsRepository.getTicketsPieData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger());
		List<NivoPieCouponDAO> outputData = new ArrayList<>();
		List<String> ticketTypes = parameters.getTicketType();

		for (TicketTypes ticketType : TicketTypes.values()) {
			try {
				if (isTicketTypeRequested(ticketTypes, ticketType.value)) {
					Long returnedValue = (Long) pieData.getClass().getMethod("get" + ticketType.methodName).invoke(pieData);
					outputData.add(new NivoPieCouponDAOExtended(ticketType.value, returnedValue));
				}
			} catch (Exception e) {
				LOGGER.warning("There was an error!");
			}
		}
		return outputData;
	}
}
