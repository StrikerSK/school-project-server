package com.charts.files.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.enums.types.PersonType;
import com.charts.api.coupon.enums.types.SellType;
import com.charts.api.coupon.enums.types.Validity;
import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.types.Months;

import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static List<UpdateCouponEntity> generateCoupons(Integer counts) {
        List<UpdateCouponEntity> enitityList = new java.util.ArrayList<>();

        for (int i = 0; i < counts; i++) {
            UpdateCouponEntity updateCouponEntity = new UpdateCouponEntity();
            updateCouponEntity.setMonth(getRandomElement(Months.class));
            updateCouponEntity.setSellType(getRandomElement(SellType.class));
            updateCouponEntity.setValidity(getRandomElement(Validity.class));
            updateCouponEntity.setPersonType(getRandomElement(PersonType.class));
            updateCouponEntity.setYear(generateYear());
            updateCouponEntity.setValue(new Random().nextInt(1000000));
            enitityList.add(updateCouponEntity);
        }

        return enitityList;
    }

    private static <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    private static <T extends IEnum> T getRandomElement(Class<T> clazz) {
        List<T> valueList = EnumUtils.getValueList(clazz);
        return getRandomElement(valueList);
    }

    private static Integer generateYear() {
        Random random = new Random();
        return random.nextInt(15) + 2010;
    }

}
