package com.javapid.entity.nivo.pie;

import com.javapid.entity.PersonType;

public class NivoPieAdultData extends NivoPieAbstractData {

    public NivoPieAdultData(Long value){
        setId(PersonType.ADULT.getValue());
        setLabel(PersonType.ADULT.getValue());
        setValue(value);
    }
}
