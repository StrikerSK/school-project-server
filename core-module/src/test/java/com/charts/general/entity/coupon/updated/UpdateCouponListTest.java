package com.charts.general.entity.coupon.updated;

import com.charts.general.entity.coupon.CouponEntity;
import com.charts.general.entity.coupon.CouponList;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.charts.general.constants.PersonType.*;
import static com.charts.general.entity.coupon.updated.UpdateCouponList.NewCouponList;

public class UpdateCouponListTest {

    CouponEntity couponEntity;

    List<UpdateCouponEntity> couponList;

    @BeforeClass
    public void setUp() {
        couponEntity = new CouponEntity();
        couponEntity.setPortable(100);
        couponEntity.setSeniors(200);
        couponEntity.setAdults(300);
        couponEntity.setStudents(400);
        couponEntity.setJunior(500);
        couponEntity.setChildren(600);
        couponEntity.setType(SellType.ESHOP);
        couponEntity.setValidity(Validity.MONTHLY);

        couponEntity.setCode("032000");
        couponEntity.setMonth("march");
        couponEntity.setYear(2000);

        couponList = NewCouponList(couponEntity);
    }

    @Test
    public void EntityConversionTest() {
        Assert.assertEquals(couponList.size(), 6);

        couponList.forEach(e -> {
            Assert.assertEquals(couponEntity.getValidity(), e.getValidity());
            Assert.assertEquals(couponEntity.getType(), e.getSellType());
            Assert.assertEquals(couponEntity.getCode(), e.getCode());
            Assert.assertEquals(couponEntity.getMonth(), e.getMonth());
            Assert.assertEquals(couponEntity.getYear(), e.getYear());
        });
    }

    @Test
    public void MultipleEntitiesTest() {
        List<CouponEntity> entities = new ArrayList<>();
        entities.add(couponEntity);
        entities.add(couponEntity);
        Assert.assertEquals(NewCouponList(new CouponList(entities)).size(), 12);
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
        Optional<UpdateCouponEntity> assertedValue = couponList.stream()
                .filter(e -> value.equals(e.getPersonType().getValue()))
                .findFirst();
        Assert.assertTrue(assertedValue.isPresent());
        Assert.assertEquals(assertedValue.get().getValue(), function.apply(couponEntity));
    }

}
