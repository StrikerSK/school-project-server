package com.charts.general.entity.coupon;

import com.charts.general.entity.AbstractEntity;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
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
                .filter(e -> months.contains(e.getMonth().getValue()))
                .collect(Collectors.toList()));
    }

    public CouponList filterByValidity(List<Validity> validities) {
        return new CouponList(coupons.stream()
                .filter(e -> validities.contains(e.getValidity()))
                .collect(Collectors.toList()));
    }

    public CouponList filterByTypes(List<SellType> types) {
        return new CouponList(coupons.stream()
                .filter(e -> types.contains(e.getType()))
                .collect(Collectors.toList()));
    }

}
