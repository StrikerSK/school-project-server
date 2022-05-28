package com.charts.general.entity.coupon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class AbstractCouponMap {

    @JsonIgnore
    protected CouponList couponList;

    @JsonProperty("data")
    protected Map<String, Integer> couponMap;

    public AbstractCouponMap(CouponList couponList) {
        this.couponList = couponList;
        this.couponMap = new HashMap<>();
    }

    public AbstractCouponMap(List<CouponEntity> list) {
        this(new CouponList(list));
    }

    @JsonIgnore
    public Integer getTotal() {
        return couponMap.values().stream().reduce(0, Integer::sum);
    }

    public abstract AbstractCouponMap calculateValues();

    public AbstractCouponMap calculateValues(CouponList couponList) {
        this.couponList = couponList;
        this.calculateValues();
        return this;
    }

    public AbstractCouponMap calculateValues(List<CouponEntity> couponEntities) {
        this.calculateValues(new CouponList(couponEntities));
        return this;
    }

}
