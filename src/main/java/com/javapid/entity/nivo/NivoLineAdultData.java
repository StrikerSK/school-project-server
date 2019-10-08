package com.javapid.entity.nivo;

import com.javapid.entity.PersonType;

import java.util.List;

public class NivoLineAdultData extends NivoLineAbstractData {

    public NivoLineAdultData(List<DataXY> data) {
        setId(PersonType.ADULT.getValue());
        setData(data);
    }
}
