package com.javapid.entity.nivo;

import com.javapid.entity.PersonType;

import java.util.List;

public class NivoLineJuniorData extends NivoLineAbstractData {

    public NivoLineJuniorData(List<DataXY> data){
        setId(PersonType.JUNIOR.getValue());
        setData(data);
    }
}
