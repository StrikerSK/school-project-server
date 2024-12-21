package com.charts.general.entity.parameters;

import com.charts.general.entity.enums.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            return EnumUtils.getValueList(Validity.class);
        } else {
            return EnumUtils.convertStringsToEnums(validity, Validity.class);
        }
    }

    public List<SellType> getSellTypes() {
        if (CollectionUtils.isEmpty(sellType)) {
            return EnumUtils.getValueList(SellType.class);
        } else {
            return EnumUtils.convertStringsToEnums(sellType, SellType.class);
        }
    }

    public List<PersonType> getPersonTypeList() {
        if (CollectionUtils.isEmpty(person)) {
            return EnumUtils.getValueList(PersonType.class);
        } else {
            return EnumUtils.convertStringsToEnums(person, PersonType.class);
        }
    }
}
