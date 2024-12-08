package com.charts.api.coupon.entity.v2;

import com.charts.general.entity.coupon.CouponsParameters;
import com.charts.api.coupon.entity.v1.CouponEntity;
import com.charts.api.coupon.entity.v1.CouponList;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import com.charts.general.utils.converter.CouponConvertor;
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
        return new UpdateCouponList(couponEntityList.stream()
                .filter(e -> months.contains(e.getMonth()))
                .collect(Collectors.toList()));
    }

    public UpdateCouponList filterByYear(List<Integer> years) {
        return new UpdateCouponList(couponEntityList.stream()
                .filter(e -> years.contains(e.getYear()))
                .collect(Collectors.toList()));
    }

    public UpdateCouponList filterByValidity(List<Validity> validityList) {
        return new UpdateCouponList(couponEntityList.stream()
                .filter(e -> validityList.contains(e.getValidity()))
                .collect(Collectors.toList()));
    }

    public UpdateCouponList filterByPersonType(List<PersonType> personTypes) {
        return new UpdateCouponList(couponEntityList.stream()
                .filter(e -> personTypes.contains(e.getPersonType()))
                .collect(Collectors.toList()));
    }

    public UpdateCouponList filterBySellType(List<SellType> sellType) {
        return new UpdateCouponList(couponEntityList.stream()
                .filter(e -> sellType.contains(e.getSellType()))
                .collect(Collectors.toList()));
    }

    public UpdateCouponList filterWithParameters(CouponsParameters parameters) {
        return filterByYear(parameters.getYearInteger())
                .filterByValidity(parameters.getValidity())
                .filterByPersonType(parameters.getPersonTypeList())
                .filterBySellType(parameters.getSellTypes())
                .filterByMonth(parameters.getMonths());
    }

    public List<PersonType> getPersonTypeValues() {
        return couponEntityList.stream()
                .map(UpdateCouponEntity::getPersonType)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<SellType> getSellTypeValues() {
        return couponEntityList.stream()
                .map(UpdateCouponEntity::getSellType)
                .distinct()
                .collect(Collectors.toList());
    }

}
