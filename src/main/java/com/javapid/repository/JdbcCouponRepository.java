package com.javapid.repository;

import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.nivo.DataXY;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCouponRepository extends JdbcAbstractRepository {

	private final JdbcTemplate jdbcTemplate;
	private final String SQL_QUERY = "SELECT " + MONTH_COLUMN + ", SUM(%s) from " + COUPON_TABLE + " WHERE %s AND %s AND %s AND %s GROUP BY " + CODE_COLUMN + "," + MONTH_COLUMN + " ORDER BY " + CODE_COLUMN + " ASC";

	public JdbcCouponRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<DataXY> fetchCouponLineData(String summedColumn, PidCouponsParameters parameters) {

		String query = String.format(SQL_QUERY,
				summedColumn,
				generateSqlByColumnQuery(VALIDITY_COLUMN, parameters.getValidity()),
				generateSqlByColumnQuery(SELL_TYPE_COLUMN, parameters.getSellType()),
				generateSqlByColumnQuery(MONTH_COLUMN, parameters.getMonth()),
				generateSqlByColumnQuery(YEAR_COLUMN, parameters.getYear())
		);

		return jdbcTemplate.query(query, (rs, rowNum) -> new DataXY(rs.getString(1), rs.getLong(2)));
	}

	//TODO add setters methods to Parameter object
	//TODO set parameter to single element array
	public Long fetchBubbleData(String summedColumn, String validityColumn, PidCouponsParameters parameters) {

		String query = String.format(SQL_QUERY,
				summedColumn,
				generateSqlByColumnQuery(VALIDITY_COLUMN, validityColumn),
				generateSqlByColumnQuery(SELL_TYPE_COLUMN, parameters.getSellType()),
				generateSqlByColumnQuery(MONTH_COLUMN, parameters.getMonth()),
				generateSqlByColumnQuery(YEAR_COLUMN, parameters.getYear())
		);

		return jdbcTemplate.query(query, (rs, rowNum) -> new DataXY(rs.getString(1), rs.getLong(2))).stream()
				.map(DataXY::getY)
				.reduce(0L, Long::sum);
	}

	public Long fetchBubbleData(String summedColumn, String validityColumn, String monthColumn, PidCouponsParameters parameters) {

		String query = String.format(SQL_QUERY,
				summedColumn,
				generateSqlByColumnQuery(VALIDITY_COLUMN, validityColumn),
				generateSqlByColumnQuery(SELL_TYPE_COLUMN, parameters.getSellType()),
				generateSqlByColumnQuery(MONTH_COLUMN, monthColumn),
				generateSqlByColumnQuery(YEAR_COLUMN, parameters.getYear())
		);

		return jdbcTemplate.query(query, (rs, rowNum) -> new DataXY(rs.getString(1), rs.getLong(2))).stream()
				.map(DataXY::getY)
				.reduce(0L, Long::sum);
	}
}
