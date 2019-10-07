package com.javapid.entity.nivo;

import com.javapid.entity.PersonType;

import java.util.List;

public class NivoSeniorLineData extends NivoAbstractLineData {

    public NivoSeniorLineData(List<DataXY> data){
        setId(PersonType.SENIOR.getValue());
        setData(data);
    }
}
