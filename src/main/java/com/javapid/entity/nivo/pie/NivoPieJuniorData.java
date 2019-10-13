package com.javapid.entity.nivo.pie;


import com.javapid.entity.enums.PersonType;

public class NivoPieJuniorData extends NivoPieAbstractData {

    public NivoPieJuniorData(Long value){
        setId(PersonType.JUNIOR.getValue());
        setLabel(PersonType.JUNIOR.getValue());
        setValue(value);
    }
}
