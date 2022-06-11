package com.charts.general.entity.coupon;

import com.charts.general.entity.MonthObject;
import com.charts.general.entity.coupon.updated.UpdateCouponEntity;
import com.charts.general.entity.coupon.updated.UpdateCouponList;
import com.charts.general.utils.CouponGroupingUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class MonthTestTest extends AbstractCouponTest {

    @Test
    public void ParseMapToObject() {
        ObjectMapper mapper = new ObjectMapper();
        MonthObject pojo = mapper.convertValue(CouponGroupingUtils.groupByMonth(new UpdateCouponList(couponEntityList).getCouponEntityList()), MonthObject.class);

        List<Map<String, Object>> marchObject = (List<Map<String, Object>>) pojo.getMarch();
        marchObject.stream()
                .map(e -> mapper.convertValue(e, UpdateCouponEntity.class))
                .forEach(e -> Assert.assertEquals(e.getMonth(), "Marec"));
    }

}
