package com.javapid.entity.nivo.line;

import com.javapid.entity.PersonType;
import com.javapid.entity.nivo.DataXY;

import java.util.List;

public class NivoLinePortableData extends NivoLineAbstractData {

    public NivoLinePortableData(List<DataXY> data){
        setId(PersonType.PORTABLE.getValue());
        setData(data);
    }
}
