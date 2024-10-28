package com.charts.apex.service;

import com.charts.apex.entity.ApexObject;
import com.charts.general.entity.coupon.CouponsParameters;
import com.charts.general.entity.coupon.v2.CouponEntityV2;
import com.charts.general.entity.coupon.v2.CouponListV2;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import com.charts.general.repository.coupon.CouponRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class AbstractApexCouponServiceTest {

    @Mock
    protected static CouponRepository couponRepository = Mockito.mock(CouponRepository.class);
    protected static ApexCouponService apexCouponService;

    @BeforeAll
    static void setUpBeforeClass() {
        CouponEntityV2 couponEntity1 = CouponEntityV2.builder().id(123L).value(100).month(Months.JANUARY).year(2015).validity(Validity.FIVE_MONTHS).sellType(SellType.COUPON).personType(PersonType.ADULT).build();
        CouponEntityV2 couponEntity2 = CouponEntityV2.builder().id(123L).value(200).month(Months.JANUARY).year(2016).validity(Validity.MONTHLY).sellType(SellType.COUPON).personType(PersonType.ADULT).build();

        CouponEntityV2 couponEntity3 = CouponEntityV2.builder().id(123L).value(300).month(Months.MARCH).year(2016).validity(Validity.FIVE_MONTHS).sellType(SellType.CARD).personType(PersonType.ADULT).build();
        CouponEntityV2 couponEntity4 = CouponEntityV2.builder().id(123L).value(400).month(Months.MARCH).year(2015).validity(Validity.MONTHLY).sellType(SellType.ESHOP).personType(PersonType.ADULT).build();

        CouponEntityV2 couponEntity5 = CouponEntityV2.builder().id(123L).value(500).month(Months.FEBRUARY).year(2016).validity(Validity.YEARLY).sellType(SellType.CARD).personType(PersonType.SENIOR).build();
        CouponEntityV2 couponEntity6 = CouponEntityV2.builder().id(123L).value(600).month(Months.FEBRUARY).year(2015).validity(Validity.MONTHLY).sellType(SellType.ESHOP).personType(PersonType.SENIOR).build();

        List<CouponEntityV2> couponList = new ArrayList<>();
        couponList.add(couponEntity1);
        couponList.add(couponEntity2);
        couponList.add(couponEntity3);
        couponList.add(couponEntity4);
        couponList.add(couponEntity5);
        couponList.add(couponEntity6);

        CouponListV2 couponListV2 = new CouponListV2(couponList);
        Mockito.when(couponRepository.getUpdateCouponList()).thenReturn(couponListV2);

        apexCouponService = new ApexCouponService(couponRepository);
    }

}