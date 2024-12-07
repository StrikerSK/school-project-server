package com.charts.general.utils.converter;

import com.charts.general.entity.coupon.AbstractCouponTest;
import com.charts.general.entity.coupon.updated.UpdateCouponEntity;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.PersonType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class CouponConvertorTest extends AbstractCouponTest {

    @Test
    public void TestCouponConverter() {
        List<UpdateCouponEntity> convertedValues = CouponConvertor.convertCouponEntity(couponEntity1);

        Assert.assertEquals(convertedValues.size(), 6);
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getCode(), "032000")));
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getYear(), 2000)));
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getMonth(), Months.MARCH)));

        Assert.assertEquals(findCouponEntity(convertedValues, PersonType.PORTABLE).getValue(), 100);
        Assert.assertEquals(findCouponEntity(convertedValues, PersonType.SENIOR).getValue(), 200);
        Assert.assertEquals(findCouponEntity(convertedValues, PersonType.ADULT).getValue(), 300);
        Assert.assertEquals(findCouponEntity(convertedValues, PersonType.STUDENT).getValue(), 400);
        Assert.assertEquals(findCouponEntity(convertedValues, PersonType.JUNIOR).getValue(), 500);
        Assert.assertEquals(findCouponEntity(convertedValues, PersonType.CHILDREN).getValue(), 600);
    }

    private UpdateCouponEntity findCouponEntity(List<UpdateCouponEntity> entities, PersonType personType) {
        return entities.stream().filter(e -> e.getPersonType() == personType).findFirst().orElseThrow(() -> new RuntimeException("Test failed!"));
    }

}
