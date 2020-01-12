package com.javapid.service;

import com.javapid.entity.enums.*;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Validators {

	/**
	 * Method verifies if provided parameters are included in Validity enum
	 * @param validities all validities requested by user
	 * @return verified validities of request
	 */
	public static List<String> verifyValidityList(@Nullable List<String> validities) {
		return verifyList(validities, getEnumList(Validity.values()));
	}

	/**
	 * Method verifies if provided parameters are included in SellType enum
	 * @param sellTypes all sell types requested by user
	 * @return verified sellTypes of request
	 */
	public static List<String> verifySellTypeList(List<String> sellTypes) {
		return verifyList(sellTypes, getEnumList(SellType.values()));
	}

	/**
	 * Method verifies if provided parameters are included in Month enum
	 * @param months all months requested by user
	 * @return verified months of request
	 */
	public static List<String> verifyMonthsList(List<String> months) {
		return verifyList(months, getEnumList(Months.values()));
	}

	public static List<String> verifyYearsList(List<String> years) {
		return verifyList(years, getEnumList(YearOptions.values()));
	}

	public static List<String> verifyPersonList(List<String> personList) {
		return verifyList(personList, getEnumList(PersonType.values()));
	}

	public static List<Integer> verifyYears(List<String> year) {
		return verifyList(year, getEnumList(YearOptions.values())).stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	public static List<String> verifyTicketType(List<String> ticketTypes){
		return verifyList(ticketTypes, getEnumList(TicketTypes.values()));
	}

	public static List<Boolean> verifyDiscountedList(List<Boolean> discounted) {
		return verifyList(discounted, Arrays.asList(true, false));
	}

	private static <T> List<T> verifyList(List<T> checkedArray, List<T> enumList) {
		try {
			checkedArray = checkedArray.stream()
					.filter(enumList::contains)
					.collect(Collectors.toList());

			if (checkedArray.isEmpty()) {
				checkedArray = enumList;
			}

			return checkedArray;
		} catch (NullPointerException e) {
			return enumList;
		}
	}

	/**
	 * Method converts enum class array to list of strings
	 * @param enumValues array of enum class values
	 * @return values array changed to list
	 */
	private static <T extends GetterValue> List<String> getEnumList(T[] enumValues) {
		return Arrays.stream(enumValues).map(T::getValue).collect(Collectors.toList());
	}

	public static Boolean isPersonTypeRequested(List<String> personList, String personType) {
		try {
			return isEmptyList(personList, PersonType.values()).contains(personType);
		} catch (NullPointerException e) {
			return true;
		}
	}

	public static Boolean isTicketTypeRequested(List<String> ticketList, String personType) {
		try {
			return isEmptyList(ticketList, TicketTypes.values()).contains(personType);
		} catch (NullPointerException e) {
			return true;
		}
	}

	/**
	 * Method verifies if provided parameter is empty or not
	 * @param inputArray verified parameter for empty list
	 * @param defaultList list of default provided values
	 * @param <G> interface for enum classes
	 * @return list of requested values
	 */
	private static <G extends GetterValue> List<String> isEmptyList(List<String> inputArray, G[] defaultList) {
		if (inputArray.isEmpty()) {
			return getEnumList(defaultList);
		}
		return inputArray;
	}
}
