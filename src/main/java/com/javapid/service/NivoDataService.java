package com.javapid.service;

import com.javapid.entity.nivo.DataSumDTO;
import com.javapid.entity.nivo.*;
import com.javapid.entity.nivo.line.*;
import com.javapid.entity.nivo.pie.*;
import com.javapid.repository.PidRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.javapid.service.Validators.*;

@Service
public class NivoDataService {

    private final PidRepository repository;

    public NivoDataService(PidRepository repository) {
        this.repository = repository;
    }

    public List<NivoLineAbstractData> getNivoLineData(List<String> validities, List<String> sellTypes, List<String> months) {
        List<NivoLineAbstractData> personList = new ArrayList<>();

        validities = verifyValidityList(validities);
        sellTypes = verifySellTypeList(sellTypes);
        months = verifyMonthsList(months);

        personList.add(new NivoLineAdultData(repository.getAdultSum(validities, sellTypes, months)));
        personList.add(new NivoLineStudentData(repository.getStudentSum(validities, sellTypes, months)));
        personList.add(new NivoLineSeniorData(repository.getSeniorSum(validities, sellTypes, months)));
        personList.add(new NivoLineJuniorData(repository.getJuniorSum(validities, sellTypes, months)));
        personList.add(new NivoLinePortableData(repository.getPortableSum(validities, sellTypes, months)));
        return personList;
    }

    public List<NivoBarData> getNivoBarData(List<String> validities, List<String> sellTypes, List<String> months) {

        validities = verifyValidityList(validities);
        sellTypes = verifySellTypeList(sellTypes);
        months = verifyMonthsList(months);

        return repository.getNivoBarData(validities, sellTypes, months);
    }

    public List<NivoPieAbstractData> getNivoPieData(List<String> validities, List<String> sellTypes, List<String> months) {

        validities = verifyValidityList(validities);
        sellTypes = verifySellTypeList(sellTypes);
        months = verifyMonthsList(months);

        DataSumDTO pieData = repository.getNivoPieData(validities, sellTypes, months);
        List<NivoPieAbstractData> outputData = new ArrayList<>();
        outputData.add(new NivoPieAdultData(pieData.getAdults()));
        outputData.add(new NivoPieStudentData(pieData.getStudents()));
        outputData.add(new NivoPieSeniorData(pieData.getSeniors()));
        outputData.add(new NivoPieJuniorData(pieData.getJuniors()));
        outputData.add(new NivoPiePortableData(pieData.getPortable()));
        return outputData;
    }
}
