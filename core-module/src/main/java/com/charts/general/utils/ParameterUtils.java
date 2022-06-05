package com.charts.general.utils;

import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.enums.Validity;
import com.charts.general.entity.enums.YearOptions;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParameterUtils {

	/**
	 * Method verifies if provided parameters are included in Validity enum
	 *
	 * @param validityList all validities requested by user
	 * @return verified validities of request
	 */
	public static List<String> verifyValidityList(List<String> validityList) {
		if (CollectionUtils.isEmpty(validityList)) {
			return Validity.validityValues();
		}

		return validityList.stream()
				.map(Validity::validityValue)
				.filter(Objects::nonNull)
				.map(Validity::getValue)
				.collect(Collectors.toList());
	}

	public static List<Validity> convertValidityList(List<String> validityList) {
		return verifyValidityList(validityList).stream()
				.map(Validity::validityValue)
				.collect(Collectors.toList());
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

	public static List<SellType> convertSellTypeList(List<String> sellTypes) {
		return verifySellTypeList(sellTypes).stream()
				.map(SellType::sellTypeValue)
				.collect(Collectors.toList());
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
		if (CollectionUtils.isEmpty(personList)) {
			return PersonType.personValues();
		}

		return personList.stream()
				.map(PersonType::personTypeValue)
				.filter(Objects::nonNull)
				.map(PersonType::getValue)
				.collect(Collectors.toList());
	}

	public static List<PersonType> convertPersonList(List<String> personList) {
		return verifyPersonList(personList).stream()
				.map(PersonType::personTypeValue)
				.collect(Collectors.toList());
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
			return enumList;
		} else {
			return checkedArray.stream()
					.filter(enumList::contains)
					.collect(Collectors.toList());
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
