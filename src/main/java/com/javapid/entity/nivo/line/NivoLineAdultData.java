package com.javapid.entity.nivo.line;

import com.javapid.entity.enums.PersonType;
import com.javapid.entity.nivo.DataXY;

import java.util.List;

public class NivoLineAdultData extends NivoLineAbstractData {

    public NivoLineAdultData(List<DataXY> data) {
        setId(PersonType.ADULT.getValue());
        setData(data);
    }
}
