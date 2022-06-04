package com.charts.general.entity.coupon;

import com.charts.general.entity.coupon.updated.UpdateCouponList;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractCouponClass {

    protected CouponEntity couponEntity1;
    protected CouponEntity couponEntity2;
    protected CouponEntity couponEntity3;

    protected UpdateCouponList updateCouponList;
    protected List<CouponEntity> couponEntityList;

    @BeforeClass
    public void setUp() {
        couponEntity1 = new CouponEntity();
        couponEntity1.setPortable(100);
        couponEntity1.setSeniors(200);
        couponEntity1.setAdults(300);
        couponEntity1.setStudents(400);
        couponEntity1.setJunior(500);
        couponEntity1.setChildren(600);
        couponEntity1.setType(SellType.ESHOP);
        couponEntity1.setValidity(Validity.YEARLY);

        couponEntity1.setCode("032000");
        couponEntity1.setMonth("March");
        couponEntity1.setYear(2000);

        couponEntity2 = new CouponEntity();
        couponEntity2.setPortable(1000);
        couponEntity2.setSeniors(2000);
        couponEntity2.setAdults(3000);
        couponEntity2.setStudents(4000);
        couponEntity2.setJunior(5000);
        couponEntity2.setChildren(6000);
        couponEntity2.setType(SellType.CARD);
        couponEntity2.setValidity(Validity.MONTHLY);

        couponEntity2.setCode("082020");
        couponEntity2.setMonth("August");
        couponEntity2.setYear(2020);

        couponEntity3 = new CouponEntity();
        couponEntity3.setPortable(10);
        couponEntity3.setSeniors(20);
        couponEntity3.setAdults(30);
        couponEntity3.setStudents(40);
        couponEntity3.setJunior(50);
        couponEntity3.setChildren(60);
        couponEntity3.setType(SellType.CARD);
        couponEntity3.setValidity(Validity.YEARLY);

        couponEntity3.setCode("122015");
        couponEntity3.setMonth("December");
        couponEntity3.setYear(2015);

        updateCouponList = new UpdateCouponList(couponEntity1);
        couponEntityList = Stream.of(couponEntity1, couponEntity2, couponEntity3).collect(Collectors.toList());
    }

}
