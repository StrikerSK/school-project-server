package com.charts.general.entity.coupon.v2;

import com.charts.general.entity.coupon.CouponsParameters;
import com.charts.general.entity.coupon.v1.CouponEntity;
import com.charts.general.entity.coupon.v1.CouponList;
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
public class UpdateCouponList {

    private final List<CouponEntityV2> couponEntityList;

    public UpdateCouponList(CouponEntity couponEntity) {
        couponEntityList = new ArrayList<>();
        this.fillData(couponEntity);
    }

    public <T> UpdateCouponList(List<T> couponList) {
        if (CollectionUtils.isNotEmpty(couponList)) {
            T listItem = couponList.get(0);
            if (listItem instanceof  CouponEntity) {
                this.couponEntityList = new ArrayList<>();
                couponList.stream()
                        .map(CouponEntity.class::cast)
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
    public UpdateCouponList(CouponList couponList) {
        this(couponList.getCoupons());
    }

    public UpdateCouponList filterByMonth(List<Months> months) {
        return new UpdateCouponList(couponEntityList.stream()
                .filter(e -> months.contains(e.getMonth()))
                .collect(Collectors.toList()));
    }

    public UpdateCouponList filterByYear(List<Integer> years) {
        return new UpdateCouponList(couponEntityList.stream()
                .filter(e -> years.contains(e.getYear()))
                .collect(Collectors.toList()));
    }

    public UpdateCouponList filterByValidity(List<Validity> validityList) {
        return new UpdateCouponList(couponEntityList.stream()
                .filter(e -> validityList.contains(e.getValidity()))
                .collect(Collectors.toList()));
    }

    public UpdateCouponList filterByPersonType(List<PersonType> personTypes) {
        return new UpdateCouponList(couponEntityList.stream()
                .filter(e -> personTypes.contains(e.getPersonType()))
                .collect(Collectors.toList()));
    }

    public UpdateCouponList filterBySellType(List<SellType> sellType) {
        return new UpdateCouponList(couponEntityList.stream()
                .filter(e -> sellType.contains(e.getSellType()))
                .collect(Collectors.toList()));
    }

    public UpdateCouponList filterWithParameters(CouponsParameters parameters) {
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

    private void fillData(CouponEntity couponEntity) {
        couponEntityList.add(extractData(PORTABLE_VALUE, couponEntity, CouponEntity::getPortable));
        couponEntityList.add(extractData(SENIOR_VALUE, couponEntity, CouponEntity::getSeniors));
        couponEntityList.add(extractData(ADULT_VALUE, couponEntity, CouponEntity::getAdults));
        couponEntityList.add(extractData(STUDENT_VALUE, couponEntity, CouponEntity::getStudents));
        couponEntityList.add(extractData(JUNIOR_VALUE, couponEntity, CouponEntity::getJunior));
        couponEntityList.add(extractData(CHILDREN_VALUE, couponEntity, CouponEntity::getChildren));
    }

    private static CouponEntityV2 extractData(String personType, CouponEntity couponEntity, Function<CouponEntity, Integer> function) {
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
