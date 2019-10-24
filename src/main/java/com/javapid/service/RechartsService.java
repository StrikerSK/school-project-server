package com.javapid.service;

import com.javapid.entity.enums.TicketTypes;
import com.javapid.entity.nivo.DataSumJizdenkyDTO;
import com.javapid.entity.nivo.NivoJizdenkyBarData;
import com.javapid.entity.nivo.line.NivoGeneralLineData;
import com.javapid.entity.nivo.line.NivoLineAbstractData;
import com.javapid.entity.nivo.pie.NivoGeneralPieData;
import com.javapid.entity.nivo.pie.NivoPieAbstractData;
import com.javapid.repository.PidJizdenkyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.javapid.service.Validators.*;

@Service
public class RechartsService {

	private final PidJizdenkyRepository pidJizdenkyRepository;

	public RechartsService(PidJizdenkyRepository pidJizdenkyRepository) {
		this.pidJizdenkyRepository = pidJizdenkyRepository;
	}

	public List<NivoLineAbstractData> getJizdenyLineData(List<Boolean> discounted, List<String> months, List<String> year, List<String> ticketTypes) {
		List<NivoLineAbstractData> personList = new ArrayList<>();

		discounted = verifyDiscountedList(discounted);
		months = verifyMonthsList(months);

		if (isTicketTypeRequested(ticketTypes, TicketTypes.FIFTEEN_MINUTES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.FIFTEEN_MINUTES.getValue(), pidJizdenkyRepository.getFifteenMinutes(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.ONE_DAY.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.ONE_DAY.getValue(), pidJizdenkyRepository.getOneDay(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.ONE_DAY_ALL.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.ONE_DAY_ALL.getValue(), pidJizdenkyRepository.getOneDayAll(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.TWO_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.TWO_ZONES.getValue(), pidJizdenkyRepository.getTwoZones(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.THREE_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.THREE_ZONES.getValue(), pidJizdenkyRepository.getThreeZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.FOUR_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.FOUR_ZONES.getValue(), pidJizdenkyRepository.getFourZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.FIVE_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.FIVE_ZONES.getValue(), pidJizdenkyRepository.getFiveZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.SIX_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.SIX_ZONES.getValue(), pidJizdenkyRepository.getSixZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.SEVEN_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.SEVEN_ZONES.getValue(), pidJizdenkyRepository.getSevenZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.EIGHT_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.EIGHT_ZONES.getValue(), pidJizdenkyRepository.getEightZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.NINE_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.NINE_ZONES.getValue(), pidJizdenkyRepository.getNineZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.TEN_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.TEN_ZONES.getValue(), pidJizdenkyRepository.getTenZone(discounted, months, verifyYears(year))));
		}

		if (isTicketTypeRequested(ticketTypes, TicketTypes.ELEVEN_ZONES.getValue())) {
			personList.add(new NivoGeneralLineData(TicketTypes.ELEVEN_ZONES.getValue(), pidJizdenkyRepository.getElevenZone(discounted, months, verifyYears(year))));
		}
		return personList;
	}

	public List<NivoJizdenkyBarData> getJizdenkyBarData(List<Boolean> discounted, List<String> months, List<String> year) {
		discounted = verifyDiscountedList(discounted);
		months = verifyMonthsList(months);

		return pidJizdenkyRepository.getJizdenkyBarData(discounted, months, verifyYears(year));
	}

	public List<NivoPieAbstractData> getJizdenkyPieData(List<Boolean> discounted, List<String> months, List<String> year, List<String> jizdenkyTypes) {
		discounted = verifyDiscountedList(discounted);
		months = verifyMonthsList(months);

		DataSumJizdenkyDTO pieData = pidJizdenkyRepository.getJizdenkyPieData(discounted, months, verifyYears(year));
		List<NivoPieAbstractData> outputData = new ArrayList<>();

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.FIFTEEN_MINUTES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.FIFTEEN_MINUTES.getValue(), pieData.getFifteenMinutes()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.ONE_DAY.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.ONE_DAY.getValue(), pieData.getOneDay()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.ONE_DAY_ALL.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.ONE_DAY_ALL.getValue(), pieData.getOneDayAll()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.TWO_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.TWO_ZONES.getValue(), pieData.getTwoZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.THREE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.THREE_ZONES.getValue(), pieData.getThreeZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.FOUR_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.FOUR_ZONES.getValue(), pieData.getFourZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.FIVE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.FIVE_ZONES.getValue(), pieData.getFiveZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.SIX_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.SIX_ZONES.getValue(), pieData.getSixZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.SEVEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.SEVEN_ZONES.getValue(), pieData.getSevenZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.TEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.EIGHT_ZONES.getValue(), pieData.getEightZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.NINE_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.NINE_ZONES.getValue(), pieData.getNineZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.TEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.TEN_ZONES.getValue(), pieData.getTenZones()));
		}

		if (isTicketTypeRequested(jizdenkyTypes, TicketTypes.ELEVEN_ZONES.getValue())) {
			outputData.add(new NivoGeneralPieData(TicketTypes.ELEVEN_ZONES.getValue(), pieData.getElevenZones()));
		}
		return outputData;
	}
}
