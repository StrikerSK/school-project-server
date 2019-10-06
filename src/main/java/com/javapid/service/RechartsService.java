package com.javapid.service;

import com.javapid.entity.PidData;
import com.javapid.objects.recharts.PersonAbstractClass;
import com.javapid.objects.recharts.AreaChartData;
import com.javapid.repository.PidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RechartsService {

    @Autowired
    private PidRepository pidRepository;

    public List<AreaChartData> getAreaChartData(Integer year, String sellType){
        List<PidData> dataList = pidRepository.getAllByYearOrderByCode(year);
        return dataList.stream()
                .filter(e -> sellType.equals(e.getType()))
                .map(DataCreator::createAreaChartData)
                .collect(Collectors.toList());
    }

    public List<List<PersonAbstractClass>> getPersonData(Integer year, String sellType){
        List<PidData> dataList = pidRepository.getAllByYearOrderByCode(year);
        return dataList.stream()
                .filter(e -> sellType.equals(e.getType()))
                .map(DataCreator::createPeronList)
                .collect(Collectors.toList());
    }
}
