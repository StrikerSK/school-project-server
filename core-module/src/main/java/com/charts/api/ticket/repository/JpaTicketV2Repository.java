package com.charts.api.ticket.repository;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTicketV2Repository extends JpaRepository<UpdateCouponEntity, Long> {
}
