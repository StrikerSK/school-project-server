package com.javapid.entity.nivo;

import java.util.List;

public class NivoPortableLineData extends NivoAbstractLineData {

    public NivoPortableLineData(List<DataXY> data){
        setId("Prenosná");
        setData(data);
    }
}
