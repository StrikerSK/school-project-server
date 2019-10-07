package com.javapid.entity.nivo;

import java.util.List;

public class NivoStudentLineData extends NivoAbstractLineData {

    public NivoStudentLineData(List<DataXY> data) {
        setId("Študenti");
        setData(data);
    }
}
