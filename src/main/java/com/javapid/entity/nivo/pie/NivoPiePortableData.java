package com.javapid.entity.nivo.pie;

import com.javapid.entity.PersonType;

public class NivoPiePortableData extends NivoPieAbstractData {

    public NivoPiePortableData(Long value){
        setId(PersonType.PORTABLE.getValue());
        setLabel(PersonType.PORTABLE.getValue());
        setValue(value);
    }
}
