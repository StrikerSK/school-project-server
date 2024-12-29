package com.charts.files.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.enums.types.PersonType;
import com.charts.api.coupon.enums.types.SellType;
import com.charts.api.coupon.enums.types.Validity;
import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.types.Months;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static List<UpdateCouponEntity> generateCoupons(Integer counts) {
        Random random = new Random();

        List<Months> monthList = EnumUtils.getValueList(Months.class);
        List<SellType> sellTypeList = EnumUtils.getValueList(SellType.class);
        List<Validity> validityList = EnumUtils.getValueList(Validity.class);
        List<PersonType> personTypeList = EnumUtils.getValueList(PersonType.class);

        List<Integer> years = new ArrayList<>();
        for (int i = 0; i < counts; i++) {
            years.add(random.nextInt(10) + 2010);
        }

        List<UpdateCouponEntity> myList = new java.util.ArrayList<>();

        for (int i = 0; i < counts; i++) {
            UpdateCouponEntity updateCouponEntity = new UpdateCouponEntity();
            updateCouponEntity.setMonth(getRandomElement(monthList));
            updateCouponEntity.setSellType(getRandomElement(sellTypeList));
            updateCouponEntity.setValidity(getRandomElement(validityList));
            updateCouponEntity.setPersonType(getRandomElement(personTypeList));
            updateCouponEntity.setYear(getRandomElement(years));
            updateCouponEntity.setValue(random.nextInt(1000000));

            myList.add(updateCouponEntity);
        }

        return myList;
    }

    private static <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }


}
