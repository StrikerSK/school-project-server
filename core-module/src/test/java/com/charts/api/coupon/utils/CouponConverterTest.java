package com.charts.api.coupon.utils;

import com.charts.api.coupon.entity.v1.CouponEntityV1;
import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.general.entity.coupon.AbstractCouponTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.charts.api.coupon.constants.EnumerationCouponConstants.*;

public class CouponConverterTest extends AbstractCouponTest {

    @Test
    public void EntityConversionTest() {
        List<UpdateCouponEntity> result = CouponConvertor.convertCouponEntity(couponEntityV11);

        Assert.assertEquals(result.size(), 6);

        result.forEach(e -> {
            Assert.assertEquals(couponEntityV11.getValidity(), e.getValidity());
            Assert.assertEquals(couponEntityV11.getType(), e.getSellType());
            Assert.assertEquals(couponEntityV11.getMonth(), e.getMonth());
            Assert.assertEquals(couponEntityV11.getYear(), e.getYear());
        });
    }

    @Test
    public void MultipleEntitiesTest() {
        Assert.assertEquals(couponV2List.size(), 18);
        Assert.assertEquals(couponV2List.size(), 18);
    }

    @Test
    public void AssertValues() {
        makeAssert(PORTABLE_VALUE, CouponEntityV1::getPortable);
        makeAssert(SENIOR_VALUE, CouponEntityV1::getSeniors);
        makeAssert(ADULT_VALUE, CouponEntityV1::getAdults);
        makeAssert(STUDENT_VALUE, CouponEntityV1::getStudents);
        makeAssert(JUNIOR_VALUE, CouponEntityV1::getJunior);
        makeAssert(CHILDREN_VALUE, CouponEntityV1::getChildren);
    }

    private void makeAssert(String value, Function<CouponEntityV1, Integer> function) {
        Optional<UpdateCouponEntity> assertedValue = singleEntryList.stream()
                .filter(e -> value.equals(e.getPersonType().getValue()))
                .findFirst();

        Assert.assertTrue(assertedValue.isPresent());
        Assert.assertEquals(assertedValue.get().getValue(), function.apply(couponEntityV11));
    }

}
