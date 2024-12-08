package com.charts.general.entity.coupon;

import com.charts.api.coupon.entity.v1.CouponEntity;
import com.charts.api.coupon.entity.v2.UpdateCouponList;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import com.charts.api.coupon.repository.JpaCouponRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

public abstract class AbstractCouponTest extends AbstractTestNGSpringContextTests {

    protected CouponEntity couponEntity1;
    protected CouponEntity couponEntity2;
    protected CouponEntity couponEntity3;

    protected UpdateCouponList updateCouponList;
    protected List<CouponEntity> couponEntityList;

    @Mock
    protected JpaCouponRepository jpaCouponRepository;

    @BeforeClass
    public void setUp() {
        couponEntity1 = CouponEntity.builder()
                .portable(100)
                .seniors(200)
                .adults(300)
                .students(400)
                .junior(500)
                .children(600)
                .type(SellType.ESHOP)
                .validity(Validity.YEARLY)
                .code("032000")
                .month(Months.MARCH)
                .year(2000)
                .build();

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
        couponEntity2.setMonth(Months.AUGUST);
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
        couponEntity3.setMonth(Months.DECEMBER);
        couponEntity3.setYear(2015);

        updateCouponList = new UpdateCouponList(couponEntity1);
        couponEntityList = Stream.of(couponEntity1, couponEntity2, couponEntity3).collect(Collectors.toList());

        this.jpaCouponRepository = Mockito.mock(JpaCouponRepository .class);
        when(jpaCouponRepository.findAll()).thenReturn(couponEntityList);
    }

}
