package com.charts.general.migration;

import com.charts.api.coupon.entity.v1.CouponEntity;
import com.charts.api.coupon.repository.JpaCouponRepository;
import com.charts.api.coupon.repository.JpaCouponV2Repository;
import com.charts.api.coupon.utils.CouponConvertor;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationListener {

    final JpaCouponRepository couponRepository;
    final JpaCouponV2Repository couponV2Repository;

    public ApplicationListener(JpaCouponRepository couponRepository, JpaCouponV2Repository couponV2Repository) {
        this.couponRepository = couponRepository;
        this.couponV2Repository = couponV2Repository;
    }

    @EventListener
    public void onApplicationEvent(SpringApplicationEvent event) {
        List<CouponEntity> coupons = couponRepository.findAll();
        couponV2Repository.saveAll(CouponConvertor.convertCouponEntity(coupons));
    }

}
