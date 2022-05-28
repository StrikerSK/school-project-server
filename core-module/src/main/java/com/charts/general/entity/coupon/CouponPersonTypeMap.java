package com.charts.general.entity.coupon;

import com.charts.general.entity.CouponEntity;
import lombok.Getter;

import java.util.List;

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
        couponMap.put(SENIOR_VALUE, couponList.getCoupons()
                .stream()
                .map(CouponEntity::getSeniors)
                .reduce(0, Integer::sum));
    }

    public void getJuniors() {
        couponMap.put(JUNIOR_VALUE, couponList.getCoupons()
                .stream()
                .map(CouponEntity::getJunior)
                .reduce(0, Integer::sum));
    }

    public void getAdults() {
        couponMap.put(ADULT_VALUE, couponList.getCoupons()
                .stream()
                .map(CouponEntity::getAdults)
                .reduce(0, Integer::sum));
    }

    public void getChildren() {
        couponMap.put(CHILDREN_VALUE, couponList.getCoupons()
                .stream()
                .map(CouponEntity::getChildren)
                .reduce(0, Integer::sum));
    }

    public void getStudents() {
        couponMap.put(STUDENT_VALUE, couponList.getCoupons()
                .stream()
                .map(CouponEntity::getStudents)
                .reduce(0, Integer::sum));
    }

    public void getPortable() {
        couponMap.put(PORTABLE_VALUE, couponList.getCoupons()
                .stream()
                .map(CouponEntity::getPortable)
                .reduce(0, Integer::sum));
    }

    public void calculateValues() {
        this.getPortable();
        this.getSeniors();
        this.getAdults();
        this.getStudents();
        this.getJuniors();
        this.getChildren();
    }

}
