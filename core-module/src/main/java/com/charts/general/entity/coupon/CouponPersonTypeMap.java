package com.charts.general.entity.coupon;

import lombok.Getter;

import java.util.List;
import java.util.function.Function;

import static com.charts.general.constants.PersonType.*;

@Getter
public class CouponPersonTypeMap extends AbstractCouponMap {

//    public static final String SENIOR_FIELD = "seniors";
//    public static final String CHILDREN_FIELD = "children";
//    public static final String JUNIOR_FIELD = "juniors";
//    public static final String ADULT_FIELD = "adults";
//    public static final String PORTABLE_FIELD = "portable";

    public CouponPersonTypeMap(CouponList couponList) {
        super(couponList);
    }

    public CouponPersonTypeMap(List<CouponEntity> couponEntities) {
        super(couponEntities);
    }

    public void getSeniors() {
        assignField(SENIOR_VALUE, CouponEntity::getSeniors);
    }

    public void getJuniors() {
        assignField(JUNIOR_VALUE, CouponEntity::getJunior);
    }

    public void getAdults() {
        assignField(ADULT_VALUE, CouponEntity::getAdults);
    }

    public void getChildren() {
        assignField(CHILDREN_VALUE, CouponEntity::getChildren);
    }

    public void getStudents() {
        assignField(STUDENT_VALUE, CouponEntity::getStudents);
    }

    public void getPortable() {
        assignField(PORTABLE_VALUE, CouponEntity::getPortable);
    }

    private void assignField(String fieldName, Function<CouponEntity, Integer> function) {
        couponMap.put(fieldName, couponList.getCoupons()
                .stream()
                .map(function)
                .reduce(0, Integer::sum));
    }

    public AbstractCouponMap calculateValues() {
        this.getPortable();
        this.getSeniors();
        this.getAdults();
        this.getStudents();
        this.getJuniors();
        this.getChildren();
        return this;
    }

}
