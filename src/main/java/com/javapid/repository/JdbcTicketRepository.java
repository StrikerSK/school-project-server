package com.javapid.repository;

import com.javapid.entity.PidTicketsParameters;
import com.javapid.entity.nivo.DataXY;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcTicketRepository extends JdbcAbstractRepository {

	private final JdbcTemplate jdbcTemplate;

	public JdbcTicketRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<DataXY> getTicketLineData(String columnName, PidTicketsParameters parameters){
		String query = String.format("SELECT " + MONTH_COLUMN + ", SUM(%s) FROM " + TICKET_TABLE + " WHERE %s AND %s AND %s GROUP BY " + CODE_COLUMN + "," + MONTH_COLUMN + " ORDER BY " + CODE_COLUMN + " ASC",
				columnName,
				generateSqlByColumnQuery(DISCOUNTED_COLUMN, parameters.getDiscounted()),
				generateSqlByColumnQuery(MONTH_COLUMN, parameters.getMonth()),
				generateSqlByColumnQuery(YEAR_COLUMN, parameters.getYear())
		);

		return jdbcTemplate.query(query, (rs, rowNum) -> new DataXY(rs.getString(1), rs.getLong(2)));
	}

	public List<Long> getApexTicketLongData(String columnName, PidTicketsParameters parameters){
		return getTicketLineData(columnName, parameters).stream()
				.map(DataXY::getY)
				.collect(Collectors.toList());
	}
}
