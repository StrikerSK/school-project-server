package com.charts.general.repository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class JdbcAbstractRepository {

	protected static final String COUPON_TABLE = "data_pid";
	protected static final String TICKET_TABLE = "pid_jizdenky";
	protected static final String VALIDITY_COLUMN = "platnost";
	protected static final String YEAR_COLUMN = "rok";
	protected static final String SELL_TYPE_COLUMN = "typ_predaja";
	protected static final String MONTH_COLUMN = "mesiac";
	protected static final String CODE_COLUMN = "kod";
	protected static final String DISCOUNTED_COLUMN = "zlavneny";

	protected  <T> String createColumnQuery(String columnName, List<T> inputList) {
		if (inputList.size() == 1) {
			return generateSingleQuery(columnName, inputList.get(0));
		} else {
			return generateMultiQuery(columnName, inputList);
		}
	}

	protected <T> String generateMultiQuery(String columnName, List<T> inputList) {
		List<String> convertedString = inputList.stream()
				.map(String::valueOf)
				.map(element -> String.format("'%s'", element))
				.collect(Collectors.toList());
		return String.format("%s IN (%s)", columnName, String.join(",",convertedString));
	}

	protected <T> String generateSingleQuery(String columnName, T inputStrings) {
		return columnName + "='" + inputStrings + "'";
	}

}
