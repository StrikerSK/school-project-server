package com.javapid.service;

import com.javapid.entity.nivo.NivoBarData;
import com.javapid.objects.recharts.PersonAbstractClass;
import com.javapid.repository.PidRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.javapid.service.Validators.*;

@Service
public class RechartsService {

    private final PidRepository pidRepository;

    public RechartsService(PidRepository pidRepository) {
        this.pidRepository = pidRepository;
    }

    public List<NivoBarData> getAreaChartData(List<String> validations, List<String> sellTypes, List<String> months, List<String> year) {
        validations = verifyValidityList(validations);
        sellTypes = verifySellTypeList(sellTypes);
        months = verifyMonthsList(months);

        List<NivoBarData> dataList = pidRepository.getNivoBarData(validations, sellTypes, months, verifyYears(year));
        return dataList.stream()
                .map(DataCreator::createAreaChartData)
                .collect(Collectors.toList());
    }

    public List<List<PersonAbstractClass>> getPersonData(List<String> validations, List<String> sellTypes, List<String> months, List<String> year) {
        validations = verifyValidityList(validations);
        sellTypes = verifySellTypeList(sellTypes);
        months = verifyMonthsList(months);

        List<NivoBarData> dataList = pidRepository.getNivoBarData(validations, sellTypes, months, verifyYears(year));
        return dataList.stream()
                .map(DataCreator::createPeronList)
                .collect(Collectors.toList());
    }
}
