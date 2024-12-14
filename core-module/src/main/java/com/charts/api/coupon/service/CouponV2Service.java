package com.charts.api.coupon.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.repository.JpaCouponV2Repository;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.Validity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.charts.general.entity.enums.Months.MONTHS_LIST;
import static com.charts.general.entity.enums.PersonType.PERSON_TYPE_LIST;
import static com.charts.general.entity.enums.SellType.SELL_TYPE_LIST;
import static com.charts.general.entity.enums.Validity.VALIDITY_LIST;

@Service
public class CouponV2Service {

    @Autowired
    private JpaCouponV2Repository couponRepository;

    public List<UpdateCouponEntity> getCouponList() { return couponRepository.findAll(); }

    public List<UpdateCouponEntity> findByPersonType(List<PersonType> personTypes) {
        List<Integer> years = new ArrayList<>();
        years.add(2022);

        return couponRepository.findAllByPersonTypeInAndValidityInAndSellTypeInAndMonthInAndYearIn(
                personTypes,
                VALIDITY_LIST,
                SELL_TYPE_LIST,
                MONTHS_LIST,
                years
        );
    }

    public List<UpdateCouponEntity> findByValidity(List<Validity> validityList) {
        List<Integer> years = new ArrayList<>();
        years.add(2022);

        return couponRepository.findAllByPersonTypeInAndValidityInAndSellTypeInAndMonthInAndYearIn(
                PERSON_TYPE_LIST,
                validityList,
                SELL_TYPE_LIST,
                MONTHS_LIST,
                years
        );
    }

    public List<UpdateCouponEntity> findByValidityAndGroup(
            List<Validity> validityList,
            String group
    ) {
        List<Integer> years = new ArrayList<>();
        years.add(2022);

        return couponRepository.findGroupedValues(
                PERSON_TYPE_LIST,
                validityList,
                SELL_TYPE_LIST,
                MONTHS_LIST,
                years,
                group
        );
    }

}
