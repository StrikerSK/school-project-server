package com.charts.general.repository.coupon;

import com.charts.general.entity.coupon.v1.CouponListV1;
import com.charts.general.entity.coupon.v2.CouponListV2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class CouponRepository {

    private final JpaCouponRepository couponRepository;

    public CouponRepository(JpaCouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public CouponListV1 getCouponList() {
        return new CouponListV1(couponRepository.findAll());
    }

    @Cacheable("couponList")
    public CouponListV2 getUpdateCouponList() {
        return new CouponListV2(couponRepository.findAll());
    }

}
