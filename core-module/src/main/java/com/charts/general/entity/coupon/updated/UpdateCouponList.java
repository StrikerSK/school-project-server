package com.charts.general.entity.coupon.updated;

import com.charts.general.entity.coupon.CouponEntity;
import com.charts.general.entity.coupon.CouponList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.charts.general.constants.PersonType.*;
import static com.charts.general.constants.PersonType.CHILDREN_VALUE;

public class UpdateCouponList {

    public static List<UpdateCouponEntity> NewCouponList(CouponEntity couponEntity) {
        List<UpdateCouponEntity> output = new ArrayList<>();
        output.add(extractData(PORTABLE_VALUE, couponEntity, CouponEntity::getPortable));
        output.add(extractData(SENIOR_VALUE, couponEntity, CouponEntity::getSeniors));
        output.add(extractData(ADULT_VALUE, couponEntity, CouponEntity::getAdults));
        output.add(extractData(STUDENT_VALUE, couponEntity, CouponEntity::getStudents));
        output.add(extractData(JUNIOR_VALUE, couponEntity, CouponEntity::getJunior));
        output.add(extractData(CHILDREN_VALUE, couponEntity, CouponEntity::getChildren));
        return output;
    }

    public static List<UpdateCouponEntity> NewCouponList(List<CouponEntity> couponList) {
        return couponList.stream()
                .flatMap(e -> NewCouponList(e).stream())
                .collect(Collectors.toList());
    }

    public static List<UpdateCouponEntity> NewCouponList(CouponList couponList) {
        return NewCouponList(couponList.getCoupons());
    }

    private static UpdateCouponEntity extractData(String personType, CouponEntity couponEntity, Function<CouponEntity, Integer> function) {
        UpdateCouponEntity output = new UpdateCouponEntity();

        //From UpdateCouponEntity class
        output.setValue(function.apply(couponEntity));
        output.setValidity(couponEntity.getValidity());
        output.setSellType(couponEntity.getType());
        output.setPersonType(personType);

        // From GeneralEntity class
        output.setMonth(couponEntity.getMonth());
        output.setYear(couponEntity.getYear());
        output.setCode(couponEntity.getCode());

        return output;
    }

}
