package com.javapid.entity.nivo;

import com.javapid.entity.PersonType;

import java.util.List;

public class NivoAdultLineData extends NivoAbstractLineData {

    public NivoAdultLineData(List<DataXY> data) {
        setId(PersonType.ADULT.getValue());
        setData(data);
    }
}
