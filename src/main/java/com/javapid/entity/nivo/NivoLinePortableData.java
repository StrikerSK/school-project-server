package com.javapid.entity.nivo;

import com.javapid.entity.PersonType;

import java.util.List;

public class NivoLinePortableData extends NivoLineAbstractData {

    public NivoLinePortableData(List<DataXY> data){
        setId(PersonType.PORTABLE.getValue());
        setData(data);
    }
}
