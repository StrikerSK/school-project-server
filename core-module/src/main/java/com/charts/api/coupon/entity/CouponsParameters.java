package com.charts.api.coupon.entity;

import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import com.charts.general.entity.parameters.AbstractParameters;

import java.util.List;

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
       return getValueList(validity, Validity.class);
    }

    public List<SellType> getSellTypes() {
       return getValueList(sellType, SellType.class);
    }

    public List<PersonType> getPersonTypeList() {
        return getValueList(person, PersonType.class);
    }

}
