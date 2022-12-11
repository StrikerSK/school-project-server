package com.charts.general.entity.coupon.v1;

import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CouponListV1 {

    private List<CouponEntityV1> coupons;

    public CouponListV1 filterByYear(List<Integer> years) {
        return new CouponListV1(coupons.stream()
                .filter(e -> years.contains(e.getYear()))
                .collect(Collectors.toList()));
    }

    public CouponListV1 filterByMonth(List<String> months) {
        return new CouponListV1(coupons.stream()
                .filter(e -> months.contains(e.getMonth().getValue()))
                .collect(Collectors.toList()));
    }

    public CouponListV1 filterByValidity(List<Validity> validities) {
        return new CouponListV1(coupons.stream()
                .filter(e -> validities.contains(e.getValidity()))
                .collect(Collectors.toList()));
    }

    public CouponListV1 filterByTypes(List<SellType> types) {
        return new CouponListV1(coupons.stream()
                .filter(e -> types.contains(e.getType()))
                .collect(Collectors.toList()));
    }

}
