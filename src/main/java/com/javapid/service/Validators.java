package com.javapid.service;

import com.javapid.entity.enums.*;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Validators {

	static List<String> verifyValidityList(@Nullable List<String> validities) {
		return verifyList(validities, getEnumList(Validity.values()));
	}

	static List<String> verifySellTypeList(List<String> sellTypes) {
		return verifyList(sellTypes, getEnumList(SellType.values()));
	}

	static List<String> verifyMonthsList(List<String> months) {
		return verifyList(months, getEnumList(Months.values()));
	}

	static List<String> verifyYearsList(List<String> years) {
		return verifyList(years, getEnumList(YearOptions.values()));
	}

	static List<String> verifyPersonList(List<String> personList) {
		return verifyList(personList, getEnumList(PersonType.values()));
	}


	static List<Integer> verifyYears(List<String> year) {
		return verifyList(year, getEnumList(YearOptions.values())).stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	static List<Boolean> verifyDiscountedList(List<Boolean> discounted) {
		return verifyList(discounted, Arrays.asList(true, false));
	}

	private static <T> List<T> verifyList(List<T> checkedArray, List<T> enumList) {
		try {
			checkedArray = checkedArray.stream().filter(enumList::contains).collect(Collectors.toList());
			if (checkedArray.isEmpty()) {
				checkedArray = enumList;
			}
			return checkedArray;
		} catch (NullPointerException e) {
			return enumList;
		}
	}

	private static <T extends ValueGetter> List<String> getEnumList(T[] enumValues) {
		return Arrays.stream(enumValues).map(T::getValue).collect(Collectors.toList());
	}

	static Boolean isPersonTypeRequested(List<String> personList, String personType) {
		try {
			return isEmptyList(personList, PersonType.values()).contains(personType);
		} catch (NullPointerException e) {
			return true;
		}
	}

	static Boolean isTicketTypeRequested(List<String> ticketList, String personType) {
		try {
			return isEmptyList(ticketList, TicketTypes.values()).contains(personType);
		} catch (NullPointerException e) {
			return true;
		}
	}

	private static <G extends ValueGetter> List<String> isEmptyList(List<String> inputArray, G[] defaultList) {
		if (inputArray.isEmpty()) {
			return getEnumList(defaultList);
		}
		return inputArray;
	}
}
