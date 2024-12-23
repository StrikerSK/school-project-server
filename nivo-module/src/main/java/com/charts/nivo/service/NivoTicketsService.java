package com.charts.nivo.service;

import com.charts.api.coupon.entity.GroupingEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.service.TicketService;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.nivo.entity.NivoDataXY;
import com.charts.general.entity.parameters.TicketsParameters;
import com.charts.api.ticket.utils.TicketGroupingUtils;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NivoTicketsService {

	private final TicketService ticketService;

	public List<NivoLineData> getTicketTypesByMonth(TicketsParameters parameters) {
		return composeLineData(parameters, TicketGroupingUtils::groupByMonth, TicketGroupingUtils::groupByTicketType);
	}

	/**
	 * Method creates two level grouping. Level one grouping contains id and list of data that is composed by level two grouping.
	 *
	 * @param parameters all parameters obtained from request
	 * @param upperGrouping top level grouping of data that which key value is used as 'id' parameter
	 * @param nestedGrouping grouping of listed data in 'data' parameter
	 */
	private <T extends IEnum, R extends IEnum> List<NivoLineData> composeLineData(
			TicketsParameters parameters,
			Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> upperGrouping,
			Function<List<UpdateTicketEntity>, Map<R, List<UpdateTicketEntity>>> nestedGrouping
	) {
		List<UpdateTicketEntity> ticketList = ticketService.getAllByFilter(parameters);
		return upperGrouping.apply(ticketList).entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey(Comparator.comparingInt(T::getOrderValue)))
				.map(e -> calculateLineData(e, nestedGrouping))
				.collect(Collectors.toList());
	}

	private  <T extends IEnum, R extends IEnum> NivoLineData calculateLineData(
			Map.Entry<T, List<UpdateTicketEntity>> mapEntry,
			Function<List<UpdateTicketEntity>, Map<R, List<UpdateTicketEntity>>> nestedGrouping
	) {
		List<NivoDataXY> summarizedGroups = nestedGrouping.apply(mapEntry.getValue()).entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey(Comparator.comparingInt(R::getOrderValue)))
				.map(entry -> new NivoDataXY(entry.getKey(), TicketGroupingUtils.aggregateGroupSum(entry.getValue()).longValue()))
				.collect(Collectors.toList());
		return new NivoLineData(mapEntry.getKey(), summarizedGroups);
	}

	public List<Map<String, Object>> getTicketBarData(TicketsParameters parameters) {
		return createTwoLevelGrouping(parameters, TicketGroupingUtils::groupByMonth, TicketGroupingUtils::groupByTicketType);
	}

	/**
	 * Method gets data for displaying bar chart. It is divided by two grouping upper grouping and nested grouping.
	 *
	 * @param parameters all parameters obtained from request
	 * @param upperGrouping top level grouping of data
	 * @param nestedGrouping nested grouping of withing upper grouping
	 */
	private <T extends IEnum, R extends IEnum> List<Map<String, Object>> createTwoLevelGrouping(
			TicketsParameters parameters,
			Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> upperGrouping,
			Function<List<UpdateTicketEntity>, Map<R, List<UpdateTicketEntity>>> nestedGrouping
	) {
		List<UpdateTicketEntity> couponList = ticketService.getAllByFilter(parameters);
		List<Map<String, Object>> outputMapList = new ArrayList<>();
		upperGrouping.apply(couponList)
				.forEach((month, entities) -> {
					Map<String, Object> outputMap = nestedGrouping.apply(entities)
							.entrySet()
							.stream()
							.map(e -> new AbstractMap.SimpleEntry<>(e.getKey().getValue(), TicketGroupingUtils.aggregateGroupSum(e.getValue())))
							.collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
					outputMap.put("month", month.getValue());
					outputMap.put("label", month.getValue());
					outputMapList.add(outputMap);
				});
		return outputMapList;
	}

	public List<NivoPieData> getTicketTypePieData(TicketsParameters parameters) {
		List<GroupingEntity<TicketTypes>> grouping = ticketService.getTicketTypesByMonth(parameters);
		return abstractFetching(grouping);
	}

	private <T extends IEnum> List<NivoPieData> abstractFetching(List<GroupingEntity<T>> grouping) {
		grouping.sort(Comparator.comparingInt(e -> e.getKey().getOrderValue()));
		return grouping
				.stream()
				.map(e -> new NivoPieData(e.getKey().getValue(), e.getValue().intValue()))
				.collect(Collectors.toList());
	}

}
