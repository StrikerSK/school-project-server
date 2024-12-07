package com.charts.general.utils.converter;

import com.charts.general.entity.coupon.CouponEntity;
import com.charts.general.entity.coupon.updated.UpdateCouponEntity;
import com.charts.general.entity.enums.PersonType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.charts.general.constants.PersonType.ADULT_VALUE;
import static com.charts.general.constants.PersonType.CHILDREN_VALUE;
import static com.charts.general.constants.PersonType.JUNIOR_VALUE;
import static com.charts.general.constants.PersonType.PORTABLE_VALUE;
import static com.charts.general.constants.PersonType.SENIOR_VALUE;
import static com.charts.general.constants.PersonType.STUDENT_VALUE;

public class CouponConvertor {

    public static List<UpdateCouponEntity> convertCouponEntity(CouponEntity couponEntity) {
        return fillData(couponEntity);
    }

    public static List<UpdateCouponEntity> convertCouponEntity(List<CouponEntity> couponEntities) {
        return couponEntities.stream()
                .map(CouponConvertor::fillData)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<UpdateCouponEntity> fillData(CouponEntity couponEntity) {
        List<UpdateCouponEntity> couponEntityList = new ArrayList<>();
        couponEntityList.add(extractData(PORTABLE_VALUE, couponEntity, CouponEntity::getPortable));
        couponEntityList.add(extractData(SENIOR_VALUE, couponEntity, CouponEntity::getSeniors));
        couponEntityList.add(extractData(ADULT_VALUE, couponEntity, CouponEntity::getAdults));
        couponEntityList.add(extractData(STUDENT_VALUE, couponEntity, CouponEntity::getStudents));
        couponEntityList.add(extractData(JUNIOR_VALUE, couponEntity, CouponEntity::getJunior));
        couponEntityList.add(extractData(CHILDREN_VALUE, couponEntity, CouponEntity::getChildren));
        return couponEntityList;
    }

    private static UpdateCouponEntity extractData(String personType, CouponEntity couponEntity, Function<CouponEntity, Integer> function) {
        UpdateCouponEntity output = new UpdateCouponEntity();

        //From UpdateCouponEntity class
        output.setValue(function.apply(couponEntity));
        output.setValidity(couponEntity.getValidity());
        output.setSellType(couponEntity.getType());
        output.setPersonType(PersonType.getPersonType(personType).get());

        // From GeneralEntity class
        output.setMonth(couponEntity.getMonth());
        output.setYear(couponEntity.getYear());
        output.setCode(couponEntity.getCode());

        return output;
    }

}
