package com.charts.nivo.Utils;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.utils.CouponGroupingUtils;
import com.charts.general.entity.enums.IEnum;
import com.charts.nivo.entity.NivoDataXY;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NivoConvertersUtils {

    public static <T extends IEnum> List<NivoDataXY> convertGroupsToDataXY(Map<T, List<UpdateCouponEntity>> groupedEntities) {
        return groupedEntities.entrySet()
                .stream()
                .map(e -> new NivoDataXY(e.getKey(), CouponGroupingUtils.sumGroup(e.getValue()).longValue()))
                .collect(Collectors.toList());
    }

}
