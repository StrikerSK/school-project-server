package com.javapid.entity.nivo.line;

import com.javapid.entity.PersonType;
import com.javapid.entity.nivo.DataXY;

import java.util.List;

public class NivoLineJuniorData extends NivoLineAbstractData {

    public NivoLineJuniorData(List<DataXY> data){
        setId(PersonType.JUNIOR.getValue());
        setData(data);
    }
}
