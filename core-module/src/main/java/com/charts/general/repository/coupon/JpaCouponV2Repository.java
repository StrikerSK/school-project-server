package com.charts.general.repository.coupon;

import com.charts.general.entity.coupon.CouponEntity;
import com.charts.general.entity.coupon.updated.UpdateCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCouponV2Repository extends JpaRepository<UpdateCouponEntity, Long> {
}
