package com.javapid.entity.nivo.line;

import com.javapid.entity.nivo.DataXY;

import java.util.List;

public class NivoGeneralLineData extends NivoLineAbstractData {

    public NivoGeneralLineData(String name, List<DataXY> data) {
        setId(name);
        setData(data);
    }

}
