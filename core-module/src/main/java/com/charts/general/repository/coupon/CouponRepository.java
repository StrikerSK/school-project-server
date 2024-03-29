package com.charts.general.repository.coupon;

import com.charts.general.entity.coupon.v1.CouponList;
import com.charts.general.entity.coupon.v2.UpdateCouponList;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("couponList")
    public UpdateCouponList getUpdateCouponList() {
        return new UpdateCouponList(couponRepository.findAll());
    }

}
