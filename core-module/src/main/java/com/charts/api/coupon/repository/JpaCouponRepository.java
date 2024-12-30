package com.charts.api.coupon.repository;

import com.charts.api.coupon.entity.v1.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCouponRepository extends JpaRepository<CouponEntity, Long> {
}
