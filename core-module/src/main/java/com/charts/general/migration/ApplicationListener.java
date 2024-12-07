package com.charts.general.migration;

import com.charts.general.entity.coupon.CouponEntity;
import com.charts.general.repository.coupon.JpaCouponRepository;
import com.charts.general.repository.coupon.JpaCouponV2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationListener {

    @Autowired
    JpaCouponRepository couponRepository;

    @Autowired
    JpaCouponV2Repository couponV2Repository;

    @EventListener
    public void onApplicationEvent(SpringApplicationEvent event) {
        List<CouponEntity> coupons = couponRepository.findAll();
        System.out.println(couponRepository.findAll());
//        System.out.println(couponList.getCouponEntityList().get(0).getId());

        System.out.println("ApplicationListener");
    }

}
