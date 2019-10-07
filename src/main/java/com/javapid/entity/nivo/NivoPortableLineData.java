package com.javapid.entity.nivo;

import com.javapid.entity.PersonType;

import java.util.List;

public class NivoPortableLineData extends NivoAbstractLineData {

    public NivoPortableLineData(List<DataXY> data){
        setId(PersonType.PORTABLE.getValue());
        setData(data);
    }
}
