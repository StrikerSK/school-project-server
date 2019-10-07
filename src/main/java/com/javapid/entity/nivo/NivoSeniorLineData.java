package com.javapid.entity.nivo;

import java.util.List;

public class NivoSeniorLineData extends NivoAbstractLineData {

    public NivoSeniorLineData(List<DataXY> data){
        setId("Dôchodcovia");
        setData(data);
    }
}
