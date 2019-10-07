package com.javapid.entity.nivo;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.List;

public abstract class NivoAbstractLineData {

    private String id;
    private List<DataXY> data;

    public NivoAbstractLineData() {
    }

    public NivoAbstractLineData(String id, List<DataXY> data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DataXY> getData() {
        return data;
    }

    public void setData(List<DataXY> data) {
        this.data = data;
    }
}
