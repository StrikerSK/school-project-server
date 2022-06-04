package com.charts.general.entity.coupon;

import com.charts.general.entity.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CouponList {

    private List<CouponEntity> coupons;

    public CouponList filterByYear(List<Integer> years) {
        return new CouponList(coupons.stream()
                .filter(e -> years.contains(e.getYear()))
                .collect(Collectors.toList()));
    }

    public CouponList filterByMonth(List<String> months) {
        return new CouponList(coupons.stream()
                .filter(e -> months.contains(e.getMonth()))
                .collect(Collectors.toList()));
    }

    public CouponList filterByValidity(List<String> validities) {
        return new CouponList(coupons.stream()
                .filter(e -> validities.contains(e.getValidity()))
                .collect(Collectors.toList()));
    }

    public CouponList filterByTypes(List<String> types) {
        return new CouponList(coupons.stream()
                .filter(e -> types.contains(e.getType()))
                .collect(Collectors.toList()));
    }

    public List<String> getMonthValues() {
        return this.getCoupons().stream()
                .map(GeneralEntity::getMonth)
                .distinct()
                .collect(Collectors.toList());
    }
}
