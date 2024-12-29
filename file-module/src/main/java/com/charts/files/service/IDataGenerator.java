package com.charts.files.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;

import java.util.List;

public interface IDataGenerator {

    List<UpdateCouponEntity> generateCoupons(Integer counts);
    List<UpdateTicketEntity> generateTickets(Integer counts);

}
