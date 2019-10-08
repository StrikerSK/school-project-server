package com.javapid.entity.nivo.line;

import com.javapid.entity.PersonType;
import com.javapid.entity.nivo.DataXY;

import java.util.List;

public class NivoLineSeniorData extends NivoLineAbstractData {

    public NivoLineSeniorData(List<DataXY> data){
        setId(PersonType.SENIOR.getValue());
        setData(data);
    }
}
