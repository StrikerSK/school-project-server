package com.charts.general;

import com.charts.general.entity.CouponEntity;
import com.charts.general.service.ICouponService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JavaPidApplicationTests {

	@Autowired
	private ICouponService couponService;

	@Test
	public void checkEmptyDatabase() {
		List<CouponEntity> dataList = couponService.getAllData();
		assertEquals(0,dataList.size());
	}

}
