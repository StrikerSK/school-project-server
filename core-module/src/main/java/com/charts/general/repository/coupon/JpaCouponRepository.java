package com.charts.general.repository.coupon;

import com.charts.general.entity.coupon.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCouponRepository extends JpaRepository<CouponEntity, Long> {
}
