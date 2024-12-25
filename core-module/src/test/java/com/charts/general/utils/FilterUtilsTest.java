package com.charts.general.utils;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.utils.CouponFilterUtils;
import com.charts.general.entity.coupon.AbstractCouponTest;
import com.charts.general.entity.enums.types.Months;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class FilterUtilsTest extends AbstractCouponTest {

    @Test
    public void testAbstractFiltering_nonEnumeration() {
        Integer year = 2000;

        List<UpdateCouponEntity> result = CouponFilterUtils.filterByYear(
                updateCouponList.getCouponEntityList(),
                Collections.singletonList(year)
        );

        Assert.assertEquals(result.size(), 6);
        result.forEach(e -> Assert.assertEquals(e.getYear(), year));
    }

    @Test
    public void testAbstractFiltering_enumeration() {
        Months searchValue = Months.MARCH;

        List<UpdateCouponEntity> result = CouponFilterUtils.filterByMonth(
                updateCouponList.getCouponEntityList(),
                Collections.singletonList(searchValue)
        );

        Assert.assertEquals(result.size(), 6);
        result.forEach(e -> Assert.assertEquals(e.getMonth(), searchValue));
    }

}
