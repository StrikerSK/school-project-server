package com.charts.api.coupon.repository;

import com.charts.api.coupon.entity.v1.CouponEntity;
import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.utils.CouponConvertor;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CouponRepository {

    private final JpaCouponRepository couponRepository;

    public List<CouponEntity> findAll() { return couponRepository.findAll(); }

    @Cacheable("couponList")
    public List<UpdateCouponEntity> getUpdateCouponList() {
        return CouponConvertor.convertCouponEntity(findAll());
    }

}
