package com.charts.api.coupon.utils;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.enums.types.PersonType;
import com.charts.api.coupon.enums.types.SellType;
import com.charts.api.coupon.enums.types.Validity;
import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.coupon.AbstractCouponTest;
import com.charts.general.entity.enums.IEnum;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CouponGroupingUtilsTest extends AbstractCouponTest {

    @Test
    public void testGrouping_personType() {
        Map<PersonType, List<UpdateCouponEntity>> map = CouponGroupingUtils.groupByPersonType(couponV2List);
        Assert.assertEquals(map.size(), 6);

        assertCoupon(map, PersonType.PORTABLE, 1200);
        assertCoupon(map, PersonType.SENIOR, 2400);
        assertCoupon(map, PersonType.ADULT, 3600);
        assertCoupon(map, PersonType.STUDENT, 4800);
        assertCoupon(map, PersonType.JUNIOR, 6000);
        assertCoupon(map, PersonType.CHILDREN, 7200);
    }

    @Test
    public void testGrouping_validity() {
        Map<Validity, List<UpdateCouponEntity>> map = CouponGroupingUtils.groupByValidity(couponV2List);
        Assert.assertEquals(map.size(), 2);

        assertCoupon(map, Validity.MONTHLY, 21000);
        assertCoupon(map, Validity.YEARLY, 4200);
    }

    @Test
    public void testGrouping_sellType() {
        Map<SellType, List<UpdateCouponEntity>> map = CouponGroupingUtils.groupBySellType(couponV2List);
        Assert.assertEquals(map.size(), 2);

        assertCoupon(map, SellType.CARD, 21000);
        assertCoupon(map, SellType.ESHOP, 4200);
    }

    private <T extends IEnum> void assertCoupon(Map<T, List<UpdateCouponEntity>> entries, T key, Integer expSum) {
        Assert.assertEquals(entries.get(key).stream().map(AbstractUpdateEntity::getValue).reduce(0, Integer::sum), expSum);
    }

}
