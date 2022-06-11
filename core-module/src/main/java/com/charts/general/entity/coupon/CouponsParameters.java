package com.charts.general.entity.coupon;

import com.charts.general.entity.AbstractParameters;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Validity> getValidity() {
        if (CollectionUtils.isEmpty(validity)) {
            return Stream.of(Validity.values()).collect(Collectors.toList());
        } else {
            return validity.stream()
                    .map(Validity::validityValue)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    public List<SellType> getSellTypes() {
        if (CollectionUtils.isEmpty(sellType)) {
            return Stream.of(SellType.values()).collect(Collectors.toList());
        } else {
            return sellType.stream()
                    .map(SellType::sellTypeValue)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    public List<PersonType> getPersonTypeList() {
        if (CollectionUtils.isEmpty(person)) {
            return Stream.of(PersonType.values()).collect(Collectors.toList());
        } else {
            return person.stream()
                    .map(PersonType::getPersonType)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }
}
