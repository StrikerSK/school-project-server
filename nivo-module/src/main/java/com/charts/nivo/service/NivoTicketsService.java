package com.charts.nivo.service;

import com.charts.general.utils.CouponGroupingUtils;
import com.charts.nivo.entity.NivoDataXY;
import com.charts.general.entity.parameters.TicketsParameters;
import com.charts.general.entity.ticket.updated.UpdateTicketList;
import com.charts.general.repository.ticket.TicketRepository;
import com.charts.general.utils.TicketGroupingUtils;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NivoTicketsService {

	private final TicketRepository ticketRepository;

	public NivoTicketsService(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	public List<NivoLineData> getTicketTypesByMonth(TicketsParameters parameters) {
		UpdateTicketList couponList = ticketRepository.getUpdatedTicketList().filterWithParameters(parameters);
		List<NivoLineData> output = new ArrayList<>();
		TicketGroupingUtils.groupByTicketType(couponList.getTicketEntities())
				.forEach((ticketType, entity) -> {
					List<NivoDataXY> nestedData = new ArrayList<>();
					TicketGroupingUtils.groupAndSumByMonth(entity)
							.forEach((month, integer) -> nestedData.add(new NivoDataXY(month, ((Integer) integer).longValue())));
					output.add(new NivoLineData(ticketType, nestedData));
				});
		return output;
	}

	public List<Map<String, Object>> getTicketBarData(TicketsParameters parameters) {
		UpdateTicketList ticketList = ticketRepository.getUpdatedTicketList().filterWithParameters(parameters);
		List<Map<String, Object>> outputMapList = new ArrayList<>();
		TicketGroupingUtils.groupByMonth(ticketList.getTicketEntities())
				.forEach((month, entities) -> {
					Map<String, Object> tmpMap = CouponGroupingUtils.convertMapKeysToString(TicketGroupingUtils.groupByAndSumByTicketType(entities));
					tmpMap.put("month", month.getValue());
					outputMapList.add(tmpMap);
				});
		return outputMapList;
	}

	public List<NivoPieData> getTicketTypePieData(TicketsParameters parameters) {
		UpdateTicketList couponList = ticketRepository.getUpdatedTicketList().filterWithParameters(parameters);
		List<NivoPieData> pieData = new ArrayList<>();
		TicketGroupingUtils.groupByAndSumByTicketType(couponList.getTicketEntities())
				.forEach((validity, total) -> pieData.add(new NivoPieData(validity, (Integer) total)));
		return pieData;
	}

}
