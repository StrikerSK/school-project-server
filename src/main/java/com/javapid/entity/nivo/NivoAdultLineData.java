package com.javapid.entity.nivo;

import java.util.List;

public class NivoAdultLineData extends NivoAbstractLineData {

    public NivoAdultLineData(List<DataXY> data) {
        setId("Dospelý");
        setData(data);
    }
}
