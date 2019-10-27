package com.javapid.service;

import com.javapid.entity.PidTicketsParameters;
import com.javapid.entity.enums.TicketTypes;
import com.javapid.entity.nivo.DataSumJizdenkyDTO;
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
import java.util.stream.Collectors;

import static com.javapid.service.Validators.*;

@Service
public class PidTicketsService extends ServiceAbstract {

	private final PidTicketsRepository pidTicketsRepository;

	private final JdbcTicketRepository jdbcTicketRepository;

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
		DataSumJizdenkyDTO pieData = pidTicketsRepository.getTicketsPieData(parameters.getDiscounted(), parameters.getMonth(), parameters.getYearInteger());
		List<NivoPieAbstractData> outputData = new ArrayList<>();
		List<String> ticketTypes = parameters.getTicketType();

		if (isTicketTypeRequested(ticketTypes, TicketTypes.FIFTEEN_MINUTES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.FIFTEEN_MINUTES.getValue(), pieData.getFifteenMinutes()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.ONE_DAY.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.ONE_DAY.getValue(), pieData.getOneDay()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.ONE_DAY_ALL.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.ONE_DAY_ALL.getValue(), pieData.getOneDayAll()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.TWO_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.TWO_ZONES.getValue(), pieData.getTwoZones()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.THREE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.THREE_ZONES.getValue(), pieData.getThreeZones()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.FOUR_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.FOUR_ZONES.getValue(), pieData.getFourZones()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.FIVE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.FIVE_ZONES.getValue(), pieData.getFiveZones()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.SIX_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.SIX_ZONES.getValue(), pieData.getSixZones()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.SEVEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.SEVEN_ZONES.getValue(), pieData.getSevenZones()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.TEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.EIGHT_ZONES.getValue(), pieData.getEightZones()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.NINE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.NINE_ZONES.getValue(), pieData.getNineZones()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.TEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.TEN_ZONES.getValue(), pieData.getTenZones()));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.ELEVEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.ELEVEN_ZONES.getValue(), pieData.getElevenZones()));
		}
		return outputData;
	}
}
