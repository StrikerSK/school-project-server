package com.charts.general.repository.coupon;

import com.charts.general.entity.coupon.CouponList;
import com.charts.general.entity.coupon.CouponPersonTypeMap;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class NewCouponRepository {

    @Autowired
    private JpaCouponRepository couponRepository;

    public CouponList getCouponList() {
        return new CouponList(couponRepository.findAll());
    }

    public CouponPersonTypeMap getCouponMap() {
        return new CouponPersonTypeMap(this.getCouponList());
    }

}
