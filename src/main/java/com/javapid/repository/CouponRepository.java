package com.javapid.repository;

import com.javapid.entity.nivo.DataXY;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CouponRepository extends RepositoryAbstract {

	private final JdbcTemplate jdbcTemplate;

	public CouponRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<DataXY> fetchCouponLineData(String summedColumn, List<String> validities, List<String> sellTypes, List<String> months, List<String> years) {

		String query = String.format("SELECT " + MONTH_COLUMN + ", SUM(%s) from " + COUPON_TABLE + " WHERE %s AND %s AND %s AND %s GROUP BY " + CODE_COLUMN + "," + MONTH_COLUMN + " ORDER BY " + CODE_COLUMN + " ASC",
				summedColumn,
				arrayToSqlString(VALIDITY_COLUMN, validities),
				arrayToSqlString(SELL_TYPE_COLUMN, sellTypes),
				arrayToSqlString(MONTH_COLUMN, months),
				arrayToSqlString(YEAR_COLUMN, years)
		);

		return jdbcTemplate.query(query, (rs, rowNum) -> new DataXY(rs.getString(1), rs.getLong(2)));
	}
}
