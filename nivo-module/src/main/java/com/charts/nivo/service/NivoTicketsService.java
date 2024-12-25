package com.charts.nivo.service;

import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.service.TicketService;
import com.charts.general.entity.enums.IEnum;
import com.charts.nivo.Utils.NivoConvertersUtils;
import com.charts.api.ticket.entity.TicketsParameters;
import com.charts.api.ticket.utils.TicketGroupingUtils;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class NivoTicketsService {

	private final TicketService ticketService;

	public List<NivoPieData> createDynamicPieData(String groupName, TicketsParameters parameters) {
		List<NivoPieData> convertedData;

		switch (groupName) {
			case "ticket":
				convertedData = NivoConvertersUtils.createPieData(ticketService.getTicketsByTicketType(parameters));
				break;
			case "discounted":
				convertedData = NivoConvertersUtils.createPieData(ticketService.getTicketsByDiscounted(parameters));
				break;
			case "month":
				convertedData = NivoConvertersUtils.createPieData(ticketService.getTicketsByMonth(parameters));
				break;
			case "year":
				convertedData = NivoConvertersUtils.createPieData(ticketService.getTicketsByYear(parameters));
				break;
			default:
				throw new IllegalArgumentException("Unknown group name: " + groupName);
		}

		convertedData.sort(Comparator.comparingInt(NivoPieData::getOrderValue));
		return convertedData;
	}

	public <T extends IEnum> List<NivoLineData> createDynamicLineData(String upperGroup, String lowerGroup, TicketsParameters parameters) {
		validateGroups(upperGroup, lowerGroup);
		Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> upperGroupingFunction = createGrouping(upperGroup);
		Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> lowerGroupingFunction = createGrouping(lowerGroup);
		return NivoConvertersUtils.createLineData(
				ticketService.getAllByFilter(parameters),
				upperGroupingFunction,
				lowerGroupingFunction,
				TicketGroupingUtils::aggregateGroupSum
		);
	}

	public <T extends IEnum> NivoBubbleData createDynamicBubbleData(String upperGroup, String lowerGroup, TicketsParameters parameters) {
		validateGroups(upperGroup, lowerGroup);
		Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> upperGroupingFunction = createGrouping(upperGroup);
		Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> lowerGroupingFunction = createGrouping(lowerGroup);
		return NivoConvertersUtils.createBubbleData(
				ticketService.getAllByFilter(parameters),
				upperGroupingFunction,
				lowerGroupingFunction,
				TicketGroupingUtils::aggregateGroupSum
		);
	}

	private void validateGroups(String upperGroup, String lowerGroup) {
		if (upperGroup.equals(lowerGroup)) {
			throw new IllegalArgumentException("Cannot use same groups!");
		}
	}

	private <T> Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> createGrouping(String groupName) {
		Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> convertedData;

		switch (groupName) {
			case "ticket":
				convertedData = (e) -> (Map<T, List<UpdateTicketEntity>>) TicketGroupingUtils.groupByTicketType(e);
				break;
			case "month":
				convertedData = (e) -> (Map<T, List<UpdateTicketEntity>>) TicketGroupingUtils.groupByMonth(e);
				break;
			case "discounted":
				convertedData = (e) -> (Map<T, List<UpdateTicketEntity>>) TicketGroupingUtils.groupByDiscounted(e);
				break;
			case "year":
				convertedData = (e) -> (Map<T, List<UpdateTicketEntity>>) TicketGroupingUtils.groupByYear(e);
				break;
			default:
				throw new IllegalArgumentException("Unknown group name: " + groupName);
		}

		return convertedData;
	}

}
