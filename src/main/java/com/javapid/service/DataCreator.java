package com.javapid.service;

import com.javapid.entity.nivo.NivoBarData;
import com.javapid.objects.recharts.*;

import java.util.ArrayList;
import java.util.List;

public class DataCreator {

    public static List<PersonAbstractClass> createPeronList(NivoBarData data) {
        List<PersonAbstractClass> personsList = new ArrayList<>();
        String month = data.getMonth();
        personsList.add(new AdultObject(month, data.getAdults()));
        personsList.add(new JuniorObject(month, data.getJuniors()));
        personsList.add(new SeniorObject(month, data.getSeniors()));
        personsList.add(new PortableObject(month, data.getPortable()));
        personsList.add(new StudentObject(month, data.getStudents()));
        return personsList;
    }
}
