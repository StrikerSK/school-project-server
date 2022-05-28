package com.charts.general.entity.coupon;

import com.charts.general.entity.AbstractDataEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CouponList {

    private List<CouponEntity> coupons;

    public void filterByYear(List<Integer> years) {
        this.coupons = coupons.stream()
                .filter(e -> years.contains(e.getYear()))
                .collect(Collectors.toList());
    }

    public void filterByMonth(List<String> months) {
        this.coupons = coupons.stream()
                .filter(e -> months.contains(e.getMonth()))
                .collect(Collectors.toList());
    }

    public void filterByValidity(List<String> validities) {
        this.coupons = coupons.stream()
                .filter(e -> validities.contains(e.getValidity()))
                .collect(Collectors.toList());
    }

    public void filterByTypes(List<String> types) {
        this.coupons = coupons.stream()
                .filter(e -> types.contains(e.getType()))
                .collect(Collectors.toList());
    }

    public CouponList withYearFiltered(List<Integer> years) {
        this.filterByYear(years);
        return this;
    }

    public CouponList withMonthFiltered(List<String> months) {
        this.filterByMonth(months);
        return this;
    }

    public CouponList withValidityFiltered(List<String> validities) {
        this.filterByValidity(validities);
        return this;
    }

    public CouponList withTypesFiltered(List<String> types) {
        this.filterByTypes(types);
        return this;
    }

    public List<String> getMonthValues() {
        return this.getCoupons().stream()
                .map(AbstractDataEntity::getMonth)
                .distinct()
                .collect(Collectors.toList());
    }
}
