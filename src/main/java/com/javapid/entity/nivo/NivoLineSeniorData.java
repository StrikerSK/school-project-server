package com.javapid.entity.nivo;

import com.javapid.entity.PersonType;

import java.util.List;

public class NivoLineSeniorData extends NivoLineAbstractData {

    public NivoLineSeniorData(List<DataXY> data){
        setId(PersonType.SENIOR.getValue());
        setData(data);
    }
}
