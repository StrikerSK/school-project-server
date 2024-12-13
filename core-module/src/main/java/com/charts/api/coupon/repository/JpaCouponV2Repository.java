package com.charts.api.coupon.repository;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface JpaCouponV2Repository extends JpaRepository<UpdateCouponEntity, Long> {
    List<UpdateCouponEntity> findAllByPersonTypeIn(Collection<PersonType> personTypes);

    List<UpdateCouponEntity> findAllByPersonTypeInAndValidityInAndSellTypeInAndMonthInAndYearIn (
            List<PersonType> personTypes,
            List<Validity> validities,
            List<SellType> sellTypes,
            List<Months> months,
            List<Integer> years
    );

}
