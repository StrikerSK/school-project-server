package com.charts.apex.service;

import com.charts.apex.entity.ApexObject;
import com.charts.api.ticket.entity.TicketsParameters;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.service.TicketService;
import com.charts.api.ticket.utils.TicketFunctionUtils;
import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.IEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApexTicketService {

	private final TicketService ticketService;

	public <T extends IEnum, R extends AbstractUpdateEntity> List<ApexObject> getTicketData(
			String upperGroup,
			String lowerGroup,
			TicketsParameters parameters
	) {
		TicketFunctionUtils.validateGroups(upperGroup, lowerGroup);

		Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> upperFunction = TicketFunctionUtils.createGrouping(upperGroup);
		Function<List<UpdateTicketEntity>, Map<T, List<UpdateTicketEntity>>> lowerFunction = TicketFunctionUtils.createGrouping(lowerGroup);

		List<UpdateTicketEntity> couponList = ticketService.getAllByFilter(parameters);
		return processValues(couponList, upperFunction, lowerFunction);
	}

	public static <T extends IEnum, R extends AbstractUpdateEntity> List<ApexObject> processValues(
			List<R> entryList,
			Function<List<R>, Map<T, List<R>>>upperFunction,
			Function<List<R>, Map<T, List<R>>> lowerFunction
	) {
		return upperFunction.apply(entryList)
				.entrySet()
				.stream()
				.sorted(Comparator.comparing(e -> e.getKey().getOrderValue()))
				.map(upper -> {
					List<Integer> sum = lowerFunction.apply(upper.getValue()).entrySet()
							.stream()
							.sorted(Comparator.comparing(e -> e.getKey().getOrderValue()))
							.map(Map.Entry::getValue)
							.map(list -> list.stream()
									.map(R::getValue)
									.reduce(0,  Integer::sum))
							.collect(Collectors.toList());
					return new ApexObject(upper.getKey(), sum);
				})
				.collect(Collectors.toList());
	}

}
