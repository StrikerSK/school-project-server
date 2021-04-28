package com.charts.general.repository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class JdbcAbstractRepository {

	final String COUPON_TABLE = "data_pid";
	final String TICKET_TABLE = "pid_jizdenky";
	final String VALIDITY_COLUMN = "platnost";
	final String YEAR_COLUMN = "rok";
	final String SELL_TYPE_COLUMN = "typ_predaja";
	final String MONTH_COLUMN = "mesiac";
	final String CODE_COLUMN = "kod";
	final String DISCOUNTED_COLUMN = "zlavneny";

	public <T> String createColumnQuery(String columnName, List<T> inputList) {
		if (inputList.size() == 1) {
			return generateSingleQuery(columnName, inputList.get(0));
		} else {
			return generateMultiQuery(columnName, inputList);
		}
	}

	private <T> String generateMultiQuery(String columnName, List<T> inputList) {
		List<String> convertedString = inputList.stream()
				.map(String::valueOf)
				.map(element -> String.format("'%s'", element))
				.collect(Collectors.toList());
		return String.format("%s IN (%s)", columnName, String.join(",",convertedString));
	}

	private <T> String generateSingleQuery(String columnName, T inputStrings) {
		return columnName + "='" + inputStrings + "'";
	}

}
