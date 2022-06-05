package com.charts.general.repository.coupon;

import com.charts.general.entity.PidCouponsParameters;
import com.charts.general.entity.nivo.DataXY;
import com.charts.general.repository.JdbcAbstractRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CouponQueryTemplate extends JdbcAbstractRepository {

	private final JdbcTemplate jdbcTemplate;
	private final String SQL_QUERY = "SELECT " + MONTH_COLUMN + ", SUM(%s) from " + COUPON_TABLE + " WHERE %s AND %s AND %s AND %s GROUP BY " + CODE_COLUMN + "," + MONTH_COLUMN + " ORDER BY " + CODE_COLUMN + " ASC";

	public CouponQueryTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<DataXY> getLineData(String summedColumn, PidCouponsParameters parameters) {

		String query = String.format(SQL_QUERY,
				summedColumn,
				createColumnQuery(VALIDITY_COLUMN, parameters.getValidity()),
				createColumnQuery(SELL_TYPE_COLUMN, parameters.getSellType()),
				createColumnQuery(MONTH_COLUMN, parameters.getMonth()),
				createColumnQuery(YEAR_COLUMN, parameters.getYear())
		);

		return jdbcTemplate.query(query, (rs, rowNum) -> new DataXY(rs.getString(1), rs.getLong(2)));
	}

	public List<Long> getAreaData(String summedColumn, PidCouponsParameters parameters) {

		return getLineData(summedColumn, parameters).stream()
				.map(DataXY::getY)
				.collect(Collectors.toList());
	}
}
