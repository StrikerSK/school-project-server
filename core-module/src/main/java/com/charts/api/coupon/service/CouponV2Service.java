package com.charts.api.coupon.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.repository.JpaCouponV2Repository;
import com.charts.general.entity.enums.PersonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponV2Service {

    @Autowired
    private JpaCouponV2Repository couponRepository;

    public List<UpdateCouponEntity> getCouponList() { return couponRepository.findAll(); }

    public List<UpdateCouponEntity> findByPersonTypes(List<PersonType> personTypes) {
        return couponRepository.findAllByPersonTypeInAndValidityInAndSellTypeInAndMonthInAndYearIn(personTypes, new ArrayList<>());
    }

}
