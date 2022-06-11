package com.charts.general.utils;

import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.TicketTypes;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ParameterUtils {

	/**
	 * Method verifies if provided parameters are included in Month enum
	 *
	 * @param months all months requested by user
	 * @return verified months of request
	 */
	public static List<String> verifyMonthsList(List<String> months) {
		return verifyList(months, Months.monthsValues());
	}

	public static List<String> verifyTicketType(List<String> ticketTypes) {
		return verifyList(ticketTypes, TicketTypes.ticketTypeValues());
	}

	public static List<Boolean> verifyDiscountedList(List<Boolean> discounted) {
		return verifyList(discounted, Arrays.asList(true, false));
	}

	private static <T> List<T> verifyList(List<T> checkedArray, List<T> enumList) {
		if(CollectionUtils.isEmpty(checkedArray)) {
			return enumList;
		} else {
			return checkedArray.stream()
					.filter(enumList::contains)
					.collect(Collectors.toList());
		}
	}
}
