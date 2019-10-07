package com.javapid.entity.nivo;

import com.javapid.entity.PersonType;

import java.util.List;

public class NivoStudentLineData extends NivoAbstractLineData {

    public NivoStudentLineData(List<DataXY> data) {
        setId(PersonType.STUDENT.getValue());
        setData(data);
    }
}
