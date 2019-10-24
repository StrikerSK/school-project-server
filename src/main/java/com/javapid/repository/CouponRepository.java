package com.javapid.repository;

import com.javapid.entity.nivo.DataXY;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CouponRepository {

	private final String VALIDITY_COLUMN = "platnost";
	private final String YEAR_COLUMN = "rok";
	private final String SELL_TYPE_COLUMN = "typ_predaja";
	private final String MONTH_COLUMN = "mesiac";
	private final String CODE_COLUMN = "kod";

	private final JdbcTemplate jdbcTemplate;

	public CouponRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<DataXY> testTemplating(String column, List<String> sellTypes, List<String> validities, List<String> months, List<String> years) {
		String query = String.format("SELECT " + MONTH_COLUMN + ", SUM(%s) from data_pid WHERE %s AND %s AND %s AND %s GROUP BY " + CODE_COLUMN + "," + MONTH_COLUMN + " ORDER BY " + CODE_COLUMN + " ASC",
				column, arrayToSqlString(SELL_TYPE_COLUMN, sellTypes), arrayToSqlString(VALIDITY_COLUMN, validities), arrayToSqlString(MONTH_COLUMN, months), arrayToSqlString(YEAR_COLUMN, years));

		return jdbcTemplate.query(query, (rs, rowNum) -> new DataXY(rs.getString(1), rs.getLong(2)));
	}

	private String arrayToSqlString(String columnName, List<String> inputStrings) {
		inputStrings = inputStrings.stream()
				.map(element -> "'" + element + "'")
				.collect(Collectors.toList());
		return columnName + " IN (" + String.join(",", inputStrings) + ")";
	}
}
