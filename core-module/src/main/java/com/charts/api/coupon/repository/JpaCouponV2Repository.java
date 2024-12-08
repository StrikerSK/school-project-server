package com.charts.api.coupon.repository;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCouponV2Repository extends JpaRepository<UpdateCouponEntity, Long> {
}
