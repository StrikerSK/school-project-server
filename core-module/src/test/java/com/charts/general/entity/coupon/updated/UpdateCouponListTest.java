package com.charts.general.entity.coupon.updated;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.entity.v2.UpdateCouponList;
import com.charts.general.entity.coupon.AbstractCouponTest;
import com.charts.api.coupon.entity.v1.CouponEntity;
import com.charts.api.coupon.entity.v1.CouponList;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.charts.api.coupon.constants.EnumerationCouponConstants.*;

public class UpdateCouponListTest extends AbstractCouponTest {

    @Test
    public void EntityConversionTest() {
        Assert.assertEquals(updateCouponList.getCouponEntityList().size(), 6);

        updateCouponList.getCouponEntityList().forEach(e -> {
            Assert.assertEquals(couponEntity1.getValidity(), e.getValidity());
            Assert.assertEquals(couponEntity1.getType(), e.getSellType());
            Assert.assertEquals(couponEntity1.getCode(), e.getCode());
            Assert.assertEquals(couponEntity1.getMonth(), e.getMonth());
            Assert.assertEquals(couponEntity1.getYear(), e.getYear());
        });
    }

    @Test
    public void MultipleEntitiesTest() {
        Assert.assertEquals(new UpdateCouponList(couponEntityList).getCouponEntityList().size(), 18);
        Assert.assertEquals(new UpdateCouponList(new CouponList(couponEntityList)).getCouponEntityList().size(), 18);
    }

    @Test
    public void TestMonthFiltering() {
        List<Months> searchList = Stream.of(Months.MARCH, Months.DECEMBER).collect(Collectors.toList());
        UpdateCouponList couponList = new UpdateCouponList(couponEntityList).filterByMonth(searchList);
        Assert.assertEquals(couponList.getCouponEntityList().size(), 12);
        couponList.getCouponEntityList().forEach(e -> Assert.assertTrue(searchList.contains(e.getMonth())));
    }

    @Test
    public void TestMonthFilteringNoResults() {
        List<Months> searchList = Stream.of(Months.JANUARY, Months.SEPTEMBER).collect(Collectors.toList());
        UpdateCouponList couponList = new UpdateCouponList(couponEntityList).filterByMonth(searchList);
        Assert.assertEquals(couponList.getCouponEntityList().size(), 0);
    }

    @Test
    public void TestPersonFiltering() {
        List<PersonType> searchList = Stream.of(PersonType.STUDENT, PersonType.ADULT, PersonType.JUNIOR).collect(Collectors.toList());
        UpdateCouponList couponList = new UpdateCouponList(couponEntityList).filterByPersonType(searchList);
        Assert.assertEquals(couponList.getCouponEntityList().size(), 9);
        couponList.getCouponEntityList().forEach(e -> Assert.assertTrue(searchList.contains(e.getPersonType())));
    }

    @Test
    public void TestYearFiltering() {
        List<Integer> searchList = Stream.of(2015, 2020).collect(Collectors.toList());
        UpdateCouponList couponList = new UpdateCouponList(couponEntityList).filterByYear(searchList);
        Assert.assertEquals(couponList.getCouponEntityList().size(), 12);
        couponList.getCouponEntityList().forEach(e -> Assert.assertTrue(searchList.contains(e.getYear())));
    }

    @Test
    public void TestYearFilteringNoResults() {
        List<Integer> searchList = Stream.of(1990, 2050).collect(Collectors.toList());
        UpdateCouponList couponList = new UpdateCouponList(couponEntityList).filterByYear(searchList);
        Assert.assertEquals(couponList.getCouponEntityList().size(), 0);
    }

    @Test
    public void TestValidityTypeFiltering() {
        List<Validity> searchList = Stream.of(Validity.MONTHLY).collect(Collectors.toList());
        UpdateCouponList couponList = new UpdateCouponList(couponEntityList).filterByValidity(searchList);
        Assert.assertEquals(couponList.getCouponEntityList().size(), 6);
        couponList.getCouponEntityList().forEach(e -> Assert.assertTrue(searchList.contains(e.getValidity())));
    }

    @Test
    public void TestValidityTypeFilteringNoResults() {
        List<Validity> searchList = Stream.of(Validity.FIVE_MONTHS).collect(Collectors.toList());
        UpdateCouponList couponList = new UpdateCouponList(couponEntityList).filterByValidity(searchList);
        Assert.assertEquals(couponList.getCouponEntityList().size(), 0);
    }

    @Test
    public void TestSellTypeFiltering() {
        List<SellType> searchList = Stream.of(SellType.CARD).collect(Collectors.toList());
        UpdateCouponList couponList = new UpdateCouponList(couponEntityList).filterBySellType(searchList);
        Assert.assertEquals(couponList.getCouponEntityList().size(), 12);
        couponList.getCouponEntityList().forEach(e -> Assert.assertTrue(searchList.contains(e.getSellType())));
    }

    @Test
    public void TestSellTypeFilteringNoResults() {
        List<SellType> searchList = Stream.of(SellType.COUPON).collect(Collectors.toList());
        UpdateCouponList couponList = new UpdateCouponList(couponEntityList).filterBySellType(searchList);
        Assert.assertEquals(couponList.getCouponEntityList().size(), 0);
    }

    @Test
    public void AssertValues() {
        makeAssert(PORTABLE_VALUE, CouponEntity::getPortable);
        makeAssert(SENIOR_VALUE, CouponEntity::getSeniors);
        makeAssert(ADULT_VALUE, CouponEntity::getAdults);
        makeAssert(STUDENT_VALUE, CouponEntity::getStudents);
        makeAssert(JUNIOR_VALUE, CouponEntity::getJunior);
        makeAssert(CHILDREN_VALUE, CouponEntity::getChildren);
    }
    private void makeAssert(String value, Function<CouponEntity, Integer> function) {
        Optional<UpdateCouponEntity> assertedValue = updateCouponList.getCouponEntityList().stream()
                .filter(e -> value.equals(e.getPersonType().getValue()))
                .findFirst();
        Assert.assertTrue(assertedValue.isPresent());
        Assert.assertEquals(assertedValue.get().getValue(), function.apply(couponEntity1));
    }

}
