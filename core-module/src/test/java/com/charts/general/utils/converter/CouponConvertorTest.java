package com.charts.general.utils.converter;

import com.charts.general.entity.coupon.AbstractCouponTest;
import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.PersonType;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class CouponConvertorTest extends AbstractCouponTest {

    @Test
    public void TestCouponConverter() {
        List<UpdateCouponEntity> convertedValues = CouponConvertor.convertCouponEntity(couponEntity1);

        Assert.assertEquals(convertedValues.size(), 6);
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getCode(), "032000")));
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getYear(), 2000)));
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getMonth(), Months.MARCH)));

        Stream.of(
                Pair.of(PersonType.PORTABLE, 100L),
                Pair.of(PersonType.SENIOR, 200L),
                Pair.of(PersonType.ADULT, 300L),
                Pair.of(PersonType.STUDENT, 400L),
                Pair.of(PersonType.JUNIOR, 500L),
                Pair.of(PersonType.CHILDREN, 600L)
        ).forEach(p -> Assert.assertEquals(findCouponEntity(convertedValues, p.getLeft()).getValue().longValue(), p.getRight(), String.format("Test failed for %s", p.getLeft())));
    }

    private UpdateCouponEntity findCouponEntity(List<UpdateCouponEntity> entities, PersonType personType) {
        return entities.stream().filter(e -> e.getPersonType() == personType).findFirst().orElseThrow(() -> new RuntimeException("Test failed!"));
    }

}
