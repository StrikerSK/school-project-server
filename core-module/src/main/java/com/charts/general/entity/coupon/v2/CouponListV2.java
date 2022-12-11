package com.charts.general.entity.coupon.v2;

import com.charts.general.entity.coupon.CouponsParameters;
import com.charts.general.entity.coupon.v1.CouponEntityV1;
import com.charts.general.entity.coupon.v1.CouponListV1;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.charts.general.constants.PersonType.*;
import static com.charts.general.constants.PersonType.CHILDREN_VALUE;

@Getter
public class CouponListV2 {

    private final List<CouponEntityV2> couponEntityList;

    public CouponListV2(CouponEntityV1 couponEntity) {
        couponEntityList = new ArrayList<>();
        this.fillData(couponEntity);
    }

    public <T> CouponListV2(List<T> couponList) {
        if (CollectionUtils.isNotEmpty(couponList)) {
            T listItem = couponList.get(0);
            if (listItem instanceof CouponEntityV1) {
                this.couponEntityList = new ArrayList<>();
                couponList.stream()
                        .map(CouponEntityV1.class::cast)
                        .forEach(this::fillData);
            } else if (listItem instanceof CouponEntityV2) {
                this.couponEntityList = couponList.stream()
                        .map(CouponEntityV2.class::cast)
                        .collect(Collectors.toList());
            } else {
                throw new IllegalArgumentException("Type cannot be determined!");
            }
        } else {
            couponEntityList = new ArrayList<>();
        }
    }
    public CouponListV2(CouponListV1 couponList) {
        this(couponList.getCoupons());
    }

    public CouponListV2 filterByMonth(List<Months> months) {
        return new CouponListV2(couponEntityList.stream()
                .filter(e -> months.contains(e.getMonth()))
                .collect(Collectors.toList()));
    }

    public CouponListV2 filterByYear(List<Integer> years) {
        return new CouponListV2(couponEntityList.stream()
                .filter(e -> years.contains(e.getYear()))
                .collect(Collectors.toList()));
    }

    public CouponListV2 filterByValidity(List<Validity> validityList) {
        return new CouponListV2(couponEntityList.stream()
                .filter(e -> validityList.contains(e.getValidity()))
                .collect(Collectors.toList()));
    }

    public CouponListV2 filterByPersonType(List<PersonType> personTypes) {
        return new CouponListV2(couponEntityList.stream()
                .filter(e -> personTypes.contains(e.getPersonType()))
                .collect(Collectors.toList()));
    }

    public CouponListV2 filterBySellType(List<SellType> sellType) {
        return new CouponListV2(couponEntityList.stream()
                .filter(e -> sellType.contains(e.getSellType()))
                .collect(Collectors.toList()));
    }

    public CouponListV2 filterWithParameters(CouponsParameters parameters) {
        return filterByYear(parameters.getYearInteger())
                .filterByValidity(parameters.getValidity())
                .filterByPersonType(parameters.getPersonTypeList())
                .filterBySellType(parameters.getSellTypes())
                .filterByMonth(parameters.getMonths());
    }

    public List<PersonType> getPersonTypeValues() {
        return couponEntityList.stream()
                .map(CouponEntityV2::getPersonType)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<SellType> getSellTypeValues() {
        return couponEntityList.stream()
                .map(CouponEntityV2::getSellType)
                .distinct()
                .collect(Collectors.toList());
    }

    private void fillData(CouponEntityV1 couponEntity) {
        couponEntityList.add(extractData(PORTABLE_VALUE, couponEntity, CouponEntityV1::getPortable));
        couponEntityList.add(extractData(SENIOR_VALUE, couponEntity, CouponEntityV1::getSeniors));
        couponEntityList.add(extractData(ADULT_VALUE, couponEntity, CouponEntityV1::getAdults));
        couponEntityList.add(extractData(STUDENT_VALUE, couponEntity, CouponEntityV1::getStudents));
        couponEntityList.add(extractData(JUNIOR_VALUE, couponEntity, CouponEntityV1::getJunior));
        couponEntityList.add(extractData(CHILDREN_VALUE, couponEntity, CouponEntityV1::getChildren));
    }

    private static CouponEntityV2 extractData(String personType, CouponEntityV1 couponEntity, Function<CouponEntityV1, Integer> function) {
        CouponEntityV2 output = new CouponEntityV2();

        //From UpdateCouponEntity class
        output.setValue(function.apply(couponEntity));
        output.setValidity(couponEntity.getValidity());
        output.setSellType(couponEntity.getType());
        output.setPersonType(PersonType.getPersonType(personType).get());

        // From GeneralEntity class
        output.setMonth(couponEntity.getMonth());
        output.setYear(couponEntity.getYear());
        output.setCode(couponEntity.getCode());

        return output;
    }

}
