package com.charts.general.repository.coupon;

import com.charts.general.entity.coupon.CouponList;
import com.charts.general.entity.coupon.CouponPersonTypeMap;
import org.springframework.stereotype.Repository;

@Repository
public class NewCouponRepository {

    private final JpaCouponRepository couponRepository;

    public NewCouponRepository(JpaCouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public CouponList getCouponList() {
        return new CouponList(couponRepository.findAll());
    }

    public CouponPersonTypeMap getCouponMap() {
        return new CouponPersonTypeMap(this.getCouponList());
    }

}
