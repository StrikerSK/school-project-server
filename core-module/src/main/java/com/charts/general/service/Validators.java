package com.charts.general.service;

import com.charts.general.entity.enums.*;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Validators {

	/**
	 * Method verifies if provided parameters are included in Validity enum
	 *
	 * @param validities all validities requested by user
	 * @return verified validities of request
	 */
	public static List<String> verifyValidityList(@Nullable List<String> validities) {
		return verifyList(validities, Validity.validityValues());
	}

	/**
	 * Method verifies if provided parameters are included in SellType enum
	 *
	 * @param sellTypes all sell types requested by user
	 * @return verified sellTypes of request
	 */
	public static List<String> verifySellTypeList(List<String> sellTypes) {
		return verifyList(sellTypes, SellType.sellTypeValues());
	}

	/**
	 * Method verifies if provided parameters are included in Month enum
	 *
	 * @param months all months requested by user
	 * @return verified months of request
	 */
	public static List<String> verifyMonthsList(List<String> months) {
		return verifyList(months, Months.monthsValues());
	}

	public static List<String> verifyYearsList(List<String> years) {
		return verifyList(years, YearOptions.yearValues());
	}

	public static List<String> verifyPersonList(List<String> personList) {
		return verifyList(personList, PersonType.personValues());
	}

	public static List<Integer> verifyYears(List<String> year) {
		return verifyList(year, YearOptions.yearValues()).stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	public static List<String> verifyTicketType(List<String> ticketTypes) {
		return verifyList(ticketTypes, TicketTypes.ticketTypeValues());
	}

	public static List<Boolean> verifyDiscountedList(List<Boolean> discounted) {
		return verifyList(discounted, Arrays.asList(true, false));
	}

	private static <T> List<T> verifyList(List<T> checkedArray, List<T> enumList) {
		if(CollectionUtils.isEmpty(checkedArray)) {
			return checkedArray.stream()
					.filter(enumList::contains)
					.collect(Collectors.toList());
		} else {
			return enumList;
		}
	}

	public static Boolean isPersonTypeRequested(List<String> personList, String personType) {
		return isEmptyList(personList, PersonType.personValues()).contains(personType);
	}

	/**
	 * Method verifies if provided parameter is empty or not
	 *
	 * @param inputArray  verified parameter for empty list
	 * @param defaultList list of default provided values
	 * @return list of requested values
	 */
	public static List<String> isEmptyList(List<String> inputArray, List<String> defaultList) {
		if(CollectionUtils.isEmpty(inputArray)) {
			return defaultList;
		} else {
			return inputArray;
		}
	}
}
