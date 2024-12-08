package com.charts.api.coupon.repository;

import com.charts.api.coupon.entity.v1.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCouponRepository extends JpaRepository<CouponEntity, Long> {
}
