package com.javapid.entity.nivo;

import com.javapid.entity.PersonType;

import java.util.List;

public class NivoLineStudentData extends NivoLineAbstractData {

    public NivoLineStudentData(List<DataXY> data) {
        setId(PersonType.STUDENT.getValue());
        setData(data);
    }
}
