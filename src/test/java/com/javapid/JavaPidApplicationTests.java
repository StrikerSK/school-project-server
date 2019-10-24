package com.javapid;

import com.javapid.entity.CouponEntity;
import com.javapid.service.PidService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaPidApplicationTests {

	@Autowired
	private PidService pidService;

	@Test
	public void checkEmptyDatabase() {
		List<CouponEntity> dataList = pidService.getAllData();
		assertEquals(0,dataList.size());
	}

	@Test
	public void addOneItem() {
		CouponEntity data = new CouponEntity("012019","Leden",2019,"Mesačná","Čipová karta",123456,234567,345678,456789,567891);
		pidService.saveData(data);
		List<CouponEntity> dataList = pidService.getAllData();
		assertEquals(1,dataList.size());
	}

}
