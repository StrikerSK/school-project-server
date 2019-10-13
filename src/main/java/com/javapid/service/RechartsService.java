package com.javapid.service;

import com.javapid.entity.nivo.NivoBarData;
import com.javapid.objects.recharts.PersonAbstractClass;
import com.javapid.repository.PidRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.javapid.service.Validators.verifySellTypeList;
import static com.javapid.service.Validators.verifyValidityList;

@Service
public class RechartsService {

    private final PidRepository pidRepository;

    public RechartsService(PidRepository pidRepository) {
        this.pidRepository = pidRepository;
    }

    public List<NivoBarData> getAreaChartData(List<String> validations, List<String> sellTypes) {
        validations = verifyValidityList(validations);
        sellTypes = verifySellTypeList(sellTypes);

        List<NivoBarData> dataList = pidRepository.getNivoBarData(validations, sellTypes);
        return dataList.stream()
                .map(DataCreator::createAreaChartData)
                .collect(Collectors.toList());
    }

    public List<List<PersonAbstractClass>> getPersonData(List<String> validations, List<String> sellTypes) {
        validations = verifyValidityList(validations);
        sellTypes = verifySellTypeList(sellTypes);

        List<NivoBarData> dataList = pidRepository.getNivoBarData(validations, sellTypes);
        return dataList.stream()
                .map(DataCreator::createPeronList)
                .collect(Collectors.toList());
    }
}
