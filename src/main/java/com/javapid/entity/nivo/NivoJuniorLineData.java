package com.javapid.entity.nivo;

import java.util.List;

public class NivoJuniorLineData extends NivoAbstractLineData {

    public NivoJuniorLineData(List<DataXY> data){
        setId("Junior");
        setData(data);
    }
}
