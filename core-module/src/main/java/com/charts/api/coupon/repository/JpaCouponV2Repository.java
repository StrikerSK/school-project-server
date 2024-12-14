package com.charts.api.coupon.repository;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaCouponV2Repository extends JpaRepository<UpdateCouponEntity, Long> {

    List<UpdateCouponEntity> findAllByPersonTypeInAndValidityInAndSellTypeInAndMonthInAndYearIn (
            List<PersonType> personTypes,
            List<Validity> validityList,
            List<SellType> sellTypes,
            List<Months> months,
            List<Integer> years
    );

    @Query(
            "SELECT c.personType, SUM(c.value) "
            + "FROM UpdateCouponEntity AS c "
            + "WHERE c.personType IN ?1 AND c.validity IN ?2 AND c.sellType IN ?3 AND c.month IN ?4 AND c.year IN ?5 "
            + "GROUP BY ?6 ORDER BY c.personType DESC"
    )
    List<UpdateCouponEntity> findGroupedValues (
            List<PersonType> personTypes,
            List<Validity> validityList,
            List<SellType> sellTypes,
            List<Months> months,
            List<Integer> years,
            String value
    );

}
