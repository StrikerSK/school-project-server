package com.charts.api.coupon.entity.v2;

import com.charts.api.coupon.utils.CouponFilterUtils;
import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.api.coupon.entity.v1.CouponEntity;
import com.charts.api.coupon.entity.v1.CouponList;
import com.charts.general.entity.enums.types.Months;
import com.charts.api.coupon.enums.types.PersonType;
import com.charts.api.coupon.enums.types.SellType;
import com.charts.api.coupon.enums.types.Validity;
import com.charts.api.coupon.utils.CouponConvertor;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UpdateCouponList {

    private final List<UpdateCouponEntity> couponEntityList;

    public UpdateCouponList(CouponEntity couponEntity) {
        this.couponEntityList = CouponConvertor.convertCouponEntity(couponEntity);
    }

    public <T> UpdateCouponList(List<T> couponList) {
        if (CollectionUtils.isNotEmpty(couponList)) {
            T listItem = couponList.get(0);
            if (listItem instanceof  CouponEntity) {
                this.couponEntityList = couponList.stream()
                        .map(CouponEntity.class::cast)
                        .map(CouponConvertor::convertCouponEntity)
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
            } else if (listItem instanceof UpdateCouponEntity) {
                this.couponEntityList = couponList.stream()
                        .map(UpdateCouponEntity.class::cast)
                        .collect(Collectors.toList());
            } else {
                throw new IllegalArgumentException("Type cannot be determined!");
            }
        } else {
            couponEntityList = new ArrayList<>();
        }
    }

    public UpdateCouponList(CouponList couponList) {
        this(couponList.getCoupons());
    }

    public UpdateCouponList filterByMonth(List<Months> months) {
        List<UpdateCouponEntity> filteredList = CouponFilterUtils.filterByMonth(couponEntityList, months);
        return new UpdateCouponList(filteredList);
    }

    public UpdateCouponList filterByYear(List<Integer> years) {
        List<UpdateCouponEntity> filteredList = CouponFilterUtils.filterByYear(couponEntityList, years);
        return new UpdateCouponList(filteredList);
    }

    public UpdateCouponList filterByValidity(List<Validity> validityList) {
        List<UpdateCouponEntity> filteredList = CouponFilterUtils.filterByValidity(couponEntityList, validityList);
        return new UpdateCouponList(filteredList);
    }

    public UpdateCouponList filterByPersonType(List<PersonType> personTypes) {
        List<UpdateCouponEntity> filteredList = CouponFilterUtils.filterByPersonType(couponEntityList, personTypes);
        return new UpdateCouponList(filteredList);
    }

    public UpdateCouponList filterBySellType(List<SellType> sellType) {
        List<UpdateCouponEntity> filteredList = CouponFilterUtils.filterBySellType(couponEntityList, sellType);
        return new UpdateCouponList(filteredList);
    }

}
