package com.charts.general.entity.coupon;

import java.util.List;

public class CouponMonthTypeMap extends AbstractCouponMap {

    public CouponMonthTypeMap(CouponList couponList) {
        super(couponList);
    }

    public CouponMonthTypeMap(List<CouponEntity> couponEntities) {
        super(couponEntities);
    }
    public AbstractCouponMap calculateValues() {
        List<String> months = couponList.getMonthValues();
        months.forEach(this::setMonthValue);
        return this;
    }

    private void setMonthValue(String month) {
        couponMap.put(month, couponList.getCoupons().stream()
                .filter(e -> e.getMonth().equals(month))
                .map(e -> e.getPortable() +  e.getSeniors() +  e.getAdults() +  e.getStudents() +  e.getJunior() + e.getChildren())
                .reduce(Integer::sum).orElse(0));
    }

}
