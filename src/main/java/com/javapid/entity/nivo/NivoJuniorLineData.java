package com.javapid.entity.nivo;

import com.javapid.entity.PersonType;

import java.util.List;

public class NivoJuniorLineData extends NivoAbstractLineData {

    public NivoJuniorLineData(List<DataXY> data){
        setId(PersonType.JUNIOR.getValue());
        setData(data);
    }
}
