package com.charts.general.utils.converter;

import com.charts.api.coupon.entity.v1.CouponEntity;
import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.general.entity.enums.PersonType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.charts.general.constants.EnumerationCouponConstants.*;

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
        List<Pair<String, Function<CouponEntity, Integer>>> rawData = Stream.of(
                Pair.of(PORTABLE_VALUE, (Function<CouponEntity, Integer>) CouponEntity::getPortable),
                Pair.of(SENIOR_VALUE, (Function<CouponEntity, Integer>) CouponEntity::getSeniors),
                Pair.of(ADULT_VALUE, (Function<CouponEntity, Integer>) CouponEntity::getAdults),
                Pair.of(STUDENT_VALUE, (Function<CouponEntity, Integer>) CouponEntity::getStudents),
                Pair.of(JUNIOR_VALUE, (Function<CouponEntity, Integer>) CouponEntity::getJunior),
                Pair.of(CHILDREN_VALUE, (Function<CouponEntity, Integer>) CouponEntity::getChildren)
        ).collect(Collectors.toList());

        return rawData
                .stream()
                .map(p -> extractData(p.getLeft(), couponEntity, p.getRight()))
                .collect(Collectors.toList());
    }

    private static UpdateCouponEntity extractData(String personType, CouponEntity couponEntity, Function<CouponEntity, Integer> function) {
        return UpdateCouponEntity.builder()
                .value(function.apply(couponEntity))
                .validity(couponEntity.getValidity())
                .sellType(couponEntity.getType())
                .personType(PersonType.getPersonType(personType).get())
                .month(couponEntity.getMonth())
                .year(couponEntity.getYear())
                .code(couponEntity.getCode())
                .build();
    }

}
