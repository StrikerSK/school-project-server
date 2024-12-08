package com.charts.general.repository;

import com.charts.general.entity.coupon.AbstractCouponTest;
import com.charts.general.repository.coupon.CouponRepository;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CouponRepositoryTest extends AbstractCouponTest {

    private CouponRepository couponRepository;

    @BeforeClass
    public void setUp() {
        super.setUp();
        this.couponRepository = new CouponRepository(jpaCouponRepository);
    }

    @Test
    public void TestFindAll() {
        Assert.assertEquals(couponRepository.getCouponList().getCoupons().size(), 3);
        Assert.assertEquals(couponRepository.getUpdateCouponList().getCouponEntityList().size(), 18);
    }

}
