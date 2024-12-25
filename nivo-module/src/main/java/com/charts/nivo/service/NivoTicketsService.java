package com.charts.nivo.service;

import com.charts.general.entity.GroupingEntity;
import com.charts.api.ticket.service.TicketService;
import com.charts.api.ticket.enums.TicketType;
import com.charts.nivo.Utils.NivoConvertersUtils;
import com.charts.api.ticket.entity.TicketsParameters;
import com.charts.api.ticket.utils.TicketGroupingUtils;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class NivoTicketsService {

	private final TicketService ticketService;

	public List<NivoLineData> getTicketTypesByMonth(TicketsParameters parameters) {
		return NivoConvertersUtils.createLineData(
				ticketService.getAllByFilter(parameters),
				TicketGroupingUtils::groupByTicketType,
				TicketGroupingUtils::groupByMonth,
				TicketGroupingUtils::aggregateGroupSum
		);
	}

	public List<Map<String, Object>> getTicketBarData(TicketsParameters parameters) {
		return NivoConvertersUtils.createBarData(
				ticketService.getAllByFilter(parameters),
				TicketGroupingUtils::groupByMonth,
				TicketGroupingUtils::groupByTicketType,
				TicketGroupingUtils::aggregateGroupSum
		);
	}

	public NivoBubbleData getTicketTypeBubbleData(TicketsParameters parameters) {
		return NivoConvertersUtils.createBubbleData(
				ticketService.getAllByFilter(parameters),
				TicketGroupingUtils::groupByTicketType,
				TicketGroupingUtils::groupByMonth,
				TicketGroupingUtils::aggregateGroupSum
		);
	}

	public List<NivoPieData> getTicketTypePieData(TicketsParameters parameters) {
		List<GroupingEntity<TicketType>> grouping = ticketService.getTicketTypesByMonth(parameters);
		return NivoConvertersUtils.createPieData(grouping);
	}

}
