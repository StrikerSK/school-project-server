package com.charts.files.utils;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.enums.types.PersonType;
import com.charts.api.coupon.enums.types.SellType;
import com.charts.api.coupon.enums.types.Validity;
import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.types.Months;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Function;

public class CsvProcessorTest {

    @Test
    public void testFileRead() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("CouponData.csv");
        List<UpdateCouponEntity> couponList = CsvProcessor.readEntries(inputStream, UpdateCouponEntity.class);

        Assert.assertEquals(couponList.size(), 6);

        Assert.assertEquals(couponList.stream().filter(e -> e.getValidity().equals(Validity.MONTHLY)).count(), 3);
        Assert.assertEquals(sumValues(couponList, Validity.MONTHLY, UpdateCouponEntity::getValidity), 600);

        Assert.assertEquals(couponList.stream().filter(e -> e.getValidity().equals(Validity.THREE_MONTHS)).count(), 3);
        Assert.assertEquals(sumValues(couponList, Validity.THREE_MONTHS, UpdateCouponEntity::getValidity), 6000);

        Assert.assertEquals(couponList.stream().filter(e -> e.getSellType().equals(SellType.CARD)).count(), 3);
        Assert.assertEquals(sumValues(couponList, SellType.CARD, UpdateCouponEntity::getSellType), 600);

        Assert.assertEquals(couponList.stream().filter(e -> e.getSellType().equals(SellType.ESHOP)).count(), 3);
        Assert.assertEquals(sumValues(couponList, SellType.ESHOP, UpdateCouponEntity::getSellType), 6000);

        Assert.assertEquals(couponList.stream().filter(e -> e.getPersonType().equals(PersonType.ADULT)).count(), 3);
        Assert.assertEquals(sumValues(couponList, PersonType.ADULT, UpdateCouponEntity::getPersonType), 600);

        Assert.assertEquals(couponList.stream().filter(e -> e.getPersonType().equals(PersonType.STUDENT)).count(), 3);
        Assert.assertEquals(sumValues(couponList, PersonType.STUDENT, UpdateCouponEntity::getPersonType), 6000);

        Assert.assertEquals(couponList.stream().filter(e -> e.getMonth().equals(Months.JANUARY)).count(), 2);
        Assert.assertEquals(sumValues(couponList, Months.JANUARY, UpdateCouponEntity::getMonth), 1100);

        Assert.assertEquals(couponList.stream().filter(e -> e.getMonth().equals(Months.FEBRUARY)).count(), 2);
        Assert.assertEquals(sumValues(couponList, Months.FEBRUARY, UpdateCouponEntity::getMonth), 2200);

        Assert.assertEquals(couponList.stream().filter(e -> e.getMonth().equals(Months.MARCH)).count(), 2);
        Assert.assertEquals(sumValues(couponList, Months.MARCH, UpdateCouponEntity::getMonth), 3300);

        Assert.assertEquals(couponList.stream().filter(e -> e.getYear().equals(2024)).count(), 6);
    }

    private <T> Integer sumValues(List<UpdateCouponEntity> inputList, T filterType, Function<UpdateCouponEntity, T> function) {
        return inputList.stream().filter(e -> function.apply(e).equals(filterType)).map(AbstractUpdateEntity::getValue).reduce(0, Integer::sum);
    }

}
