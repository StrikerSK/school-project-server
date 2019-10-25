package com.javapid.repository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class RepositoryAbstract {

	final String COUPON_TABLE = "data_pid";
	final String VALIDITY_COLUMN = "platnost";
	final String YEAR_COLUMN = "rok";
	final String SELL_TYPE_COLUMN = "typ_predaja";
	final String MONTH_COLUMN = "mesiac";
	final String CODE_COLUMN = "kod";
	final String DISCOUNTED_COLUMN = "zlavneny";

	String arrayToSqlString(String columnName, List<String> inputStrings) {
		inputStrings = inputStrings.stream()
				.map(element -> "'" + element + "'")
				.collect(Collectors.toList());
		return columnName + " IN (" + String.join(",", inputStrings) + ")";
	}

}
