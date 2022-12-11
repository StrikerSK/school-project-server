package com.charts.general.repository.coupon;

import com.charts.general.entity.coupon.v1.CouponEntityV1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCouponRepository extends JpaRepository<CouponEntityV1, Long> {
}
