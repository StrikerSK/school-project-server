package com.charts.api.coupon.service;

import com.charts.api.coupon.entity.GroupingEntity;
import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.repository.JpaCouponV2Repository;
import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponV2Service {

    private final JpaCouponV2Repository couponRepository;

    public CouponV2Service(JpaCouponV2Repository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public List<UpdateCouponEntity> findCouponEntities(CouponsParameters couponsParameters) {
        return couponRepository.findAllByPersonTypeInAndValidityInAndSellTypeInAndMonthInAndYearIn(
                couponsParameters.getPersonTypeList(),
                couponsParameters.getValidity(),
                couponsParameters.getSellTypes(),
                couponsParameters.getMonths(),
                couponsParameters.getYearInteger()
        );
    }

    public List<GroupingEntity<Months>> findByValidityAndGroupedByMonth(CouponsParameters couponsParameters) {
        return couponRepository.findGroupedByMonthValues(
                couponsParameters.getPersonTypeList(),
                couponsParameters.getValidity(),
                couponsParameters.getSellTypes(),
                couponsParameters.getMonths(),
                couponsParameters.getYearInteger()
        );
    }

    public List<GroupingEntity<PersonType>> findByValidityAndGroupedByPersonType(CouponsParameters couponsParameters) {
        return couponRepository.findGroupedByPersonTypeValues(
                couponsParameters.getPersonTypeList(),
                couponsParameters.getValidity(),
                couponsParameters.getSellTypes(),
                couponsParameters.getMonths(),
                couponsParameters.getYearInteger()
        );
    }

    public List<GroupingEntity<Validity>> findByValidityAndGroupedByValidity(CouponsParameters couponsParameters) {
        return couponRepository.findGroupedByValidityValues(
                couponsParameters.getPersonTypeList(),
                couponsParameters.getValidity(),
                couponsParameters.getSellTypes(),
                couponsParameters.getMonths(),
                couponsParameters.getYearInteger()
        );
    }

    public List<GroupingEntity<SellType>> findByValidityAndGroupedBySellType(CouponsParameters couponsParameters) {
        return couponRepository.findGroupedBySellTypeValues(
                couponsParameters.getPersonTypeList(),
                couponsParameters.getValidity(),
                couponsParameters.getSellTypes(),
                couponsParameters.getMonths(),
                couponsParameters.getYearInteger()
        );
    }

}
