package com.charts.general.entity.coupon;

import com.charts.general.entity.AbstractParameters;
import com.charts.general.entity.enums.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.charts.general.entity.enums.EnumTypes.*;

public class CouponsParameters extends AbstractParameters {

    private final List<String> validity;
    private final List<String> sellType;
    private final List<String> person;

    public CouponsParameters(List<String> validity, List<String> sellType, List<String> month, List<Integer> year, List<String> person) {
        super(month, year);
        this.validity = validity;
        this.sellType = sellType;
        this.person = person;
    }

    public List<IEnum> getValidity() {
        if (CollectionUtils.isEmpty(validity)) {
            return EnumUtils.getValueList(VALIDITY_ENUM);
        } else {
            return validity.stream()
                    .map(Validity::validityValue)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    public List<IEnum> getSellTypes() {
        if (CollectionUtils.isEmpty(sellType)) {
            return EnumUtils.getValueList(SELL_TYPE_ENUM);
        } else {
            return sellType.stream()
                    .map(SellType::sellTypeValue)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    public List<IEnum> getPersonTypeList() {
        if (CollectionUtils.isEmpty(person)) {
            return EnumUtils.getValueList(PERSON_TYPE_ENUM);
        } else {
            return person.stream()
                    .map(PersonType::getPersonType)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }
}
