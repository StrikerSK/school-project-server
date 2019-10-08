package com.javapid.entity.nivo.line;

import com.javapid.entity.PersonType;
import com.javapid.entity.nivo.DataXY;

import java.util.List;

public class NivoLineStudentData extends NivoLineAbstractData {

    public NivoLineStudentData(List<DataXY> data) {
        setId(PersonType.STUDENT.getValue());
        setData(data);
    }
}
