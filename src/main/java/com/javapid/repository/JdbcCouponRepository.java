package com.javapid.repository;

import com.javapid.entity.PidCouponsParameters;
import com.javapid.entity.nivo.DataXY;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCouponRepository extends JdbcAbstractRepository {

	private final JdbcTemplate jdbcTemplate;

	public JdbcCouponRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<DataXY> fetchCouponLineData(String summedColumn, PidCouponsParameters parameters) {

		String query = String.format("SELECT " + MONTH_COLUMN + ", SUM(%s) from " + COUPON_TABLE + " WHERE %s AND %s AND %s AND %s GROUP BY " + CODE_COLUMN + "," + MONTH_COLUMN + " ORDER BY " + CODE_COLUMN + " ASC",
				summedColumn,
				arrayToSqlString(VALIDITY_COLUMN, parameters.getValidity()),
				arrayToSqlString(SELL_TYPE_COLUMN, parameters.getSellType()),
				arrayToSqlString(MONTH_COLUMN, parameters.getMonth()),
				arrayToSqlString(YEAR_COLUMN, parameters.getYear())
		);

		return jdbcTemplate.query(query, (rs, rowNum) -> new DataXY(rs.getString(1), rs.getLong(2)));
	}
}
