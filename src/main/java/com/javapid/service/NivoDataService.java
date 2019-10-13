package com.javapid.service;

import com.javapid.entity.enums.SellType;
import com.javapid.entity.enums.Validity;
import com.javapid.entity.nivo.DataSumDTO;
import com.javapid.entity.nivo.*;
import com.javapid.entity.nivo.line.*;
import com.javapid.entity.nivo.pie.*;
import com.javapid.repository.PidRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NivoDataService {

    private final PidRepository repository;

    public NivoDataService(PidRepository repository) {
        this.repository = repository;
    }

    public List<NivoLineAbstractData> getNivoLineData(List<String> validities, List<String> sellTypes) {
        List<NivoLineAbstractData> personList = new ArrayList<>();

        validities = verifyValidityList(validities);
        sellTypes = verifySellTypeList(sellTypes);

        personList.add(new NivoLineAdultData(repository.getAdultSum(validities, sellTypes)));
        personList.add(new NivoLineStudentData(repository.getStudentSum(validities, sellTypes)));
        personList.add(new NivoLineSeniorData(repository.getSeniorSum(validities, sellTypes)));
        personList.add(new NivoLineJuniorData(repository.getJuniorSum(validities, sellTypes)));
        personList.add(new NivoLinePortableData(repository.getPortableSum(validities, sellTypes)));
        return personList;
    }

    public List<NivoBarData> getNivoBarData(List<String> validities, List<String> sellTypes) {
        validities = verifyValidityList(validities);
		sellTypes = verifySellTypeList(sellTypes);

        return repository.getNivoBarData(validities, sellTypes);
    }

    public List<NivoPieAbstractData> getNivoPieData(List<String> validities, List<String> sellTypes) {
        validities = verifyValidityList(validities);
		sellTypes = verifySellTypeList(sellTypes);

        DataSumDTO pieData = repository.getNivoPieData(validities, sellTypes);
        List<NivoPieAbstractData> outputData = new ArrayList<>();
        outputData.add(new NivoPieAdultData(pieData.getAdults()));
        outputData.add(new NivoPieStudentData(pieData.getStudents()));
        outputData.add(new NivoPieSeniorData(pieData.getSeniors()));
        outputData.add(new NivoPieJuniorData(pieData.getJuniors()));
        outputData.add(new NivoPiePortableData(pieData.getPortable()));
        return outputData;
    }

    private List<String> verifyValidityList(List<String> validities) {
        if (validities == null) {
            validities = Arrays.asList(Validity.MONTHLY.getValue(), Validity.FIVE_MONTHS.getValue(), Validity.THREE_MONTHS.getValue(), Validity.YEARLY.getValue());
        }
        return validities;
    }

    private List<String> verifySellTypeList(List<String> sellTypes) {
        if (sellTypes == null) {
            sellTypes = Arrays.asList(SellType.CARD.getValue(), SellType.COUPON.getValue());
        }
        return sellTypes;
    }
}
