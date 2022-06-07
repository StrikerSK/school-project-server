package com.charts.general.repository.coupon;

import com.charts.general.entity.coupon.CouponList;
import com.charts.general.entity.coupon.updated.UpdateCouponList;
import org.springframework.stereotype.Repository;

@Repository
public class CouponRepository {

    private final JpaCouponRepository couponRepository;

    public CouponRepository(JpaCouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public CouponList getCouponList() {
        return new CouponList(couponRepository.findAll());
    }

    public UpdateCouponList getUpdateCouponList() {
        return new UpdateCouponList(couponRepository.findAll());
    }

}
