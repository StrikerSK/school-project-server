package com.charts.general.repository.coupon;

import com.charts.general.entity.coupon.v1.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCouponRepository extends JpaRepository<CouponEntity, Long> {
}
