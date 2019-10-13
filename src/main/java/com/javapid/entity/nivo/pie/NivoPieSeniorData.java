package com.javapid.entity.nivo.pie;

import com.javapid.entity.enums.PersonType;

public class NivoPieSeniorData extends NivoPieAbstractData {

    public NivoPieSeniorData(Long value){
        setId(PersonType.SENIOR.getValue());
        setLabel(PersonType.SENIOR.getValue());
        setValue(value);
    }
}
