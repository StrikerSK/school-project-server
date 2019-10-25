package com.javapid.repository;

import com.javapid.entity.nivo.DataXY;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository extends RepositoryAbstract {

	private final JdbcTemplate jdbcTemplate;

	public TicketRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<DataXY> getTicketLineData(String columnName, List<String> discounted, List<String> months, List<String> years){
		String query = String.format("SELECT " + MONTH_COLUMN + ", SUM(%s) from TicketEntity WHERE %s AND %s AND %s GROUP BY " + CODE_COLUMN + "," + MONTH_COLUMN + " ORDER BY " + CODE_COLUMN + " ASC",
				columnName,
				arrayToSqlString(DISCOUNTED_COLUMN, discounted),
				arrayToSqlString(MONTH_COLUMN, months),
				arrayToSqlString(YEAR_COLUMN, years)
		);

		return jdbcTemplate.query(query, (rs, rowNum) -> new DataXY(rs.getString(1), rs.getLong(2)));
	}
}
