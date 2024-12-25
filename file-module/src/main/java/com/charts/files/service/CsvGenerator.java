package com.charts.files.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CsvGenerator {

    private final CouponV2Service couponService;

    private static final String CSV_HEADER = "Year,Month,Validity,PersonType,SellType\n";

    public String generateCouponCsv() {
        List<UpdateCouponEntity> coupons = couponService.findAll();
        StringBuilder csvContent = new StringBuilder();
        csvContent.append(CSV_HEADER);

        for (UpdateCouponEntity coupon : coupons) {
            csvContent
                    .append(coupon.getYear()).append(",")
                    .append(coupon.getMonth().getValue()).append(",")
                    .append(coupon.getValidity().getValue()).append(",")
                    .append(coupon.getPersonType().getValue()).append(",")
                    .append(coupon.getSellType().getValue()).append("\n");
        }

        return csvContent.toString();
    }
}
