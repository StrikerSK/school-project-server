package com.javapid.service;

import com.javapid.entity.enums.JizdenkyTypes;
import com.javapid.entity.nivo.DataSumDTO;
import com.javapid.entity.nivo.*;
import com.javapid.entity.nivo.line.*;
import com.javapid.entity.nivo.pie.*;
import com.javapid.repository.PidJizdenkyRepository;
import com.javapid.repository.PidRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.javapid.service.Validators.*;

@Service
public class NivoDataService {

    private final PidRepository repository;

    private final PidJizdenkyRepository jizdenkyRepository;

    public NivoDataService(PidRepository repository, PidJizdenkyRepository jizdenkyRepository) {
        this.repository = repository;
        this.jizdenkyRepository = jizdenkyRepository;
    }

    public List<NivoLineAbstractData> getNivoLineData(List<String> validities, List<String> sellTypes, List<String> months, List<String> year) {
        List<NivoLineAbstractData> personList = new ArrayList<>();

        validities = verifyValidityList(validities);
        sellTypes = verifySellTypeList(sellTypes);
        months = verifyMonthsList(months);

        personList.add(new NivoLineAdultData(repository.getAdultSum(validities, sellTypes, months, verifyYears(year))));
        personList.add(new NivoLineStudentData(repository.getStudentSum(validities, sellTypes, months, verifyYears(year))));
        personList.add(new NivoLineSeniorData(repository.getSeniorSum(validities, sellTypes, months, verifyYears(year))));
        personList.add(new NivoLineJuniorData(repository.getJuniorSum(validities, sellTypes, months, verifyYears(year))));
        personList.add(new NivoLinePortableData(repository.getPortableSum(validities, sellTypes, months, verifyYears(year))));
        return personList;
    }

    public List<NivoBarData> getNivoBarData(List<String> validities, List<String> sellTypes, List<String> months, List<String> year) {

        validities = verifyValidityList(validities);
        sellTypes = verifySellTypeList(sellTypes);
        months = verifyMonthsList(months);

        return repository.getNivoBarData(validities, sellTypes, months, verifyYears(year));
    }

    public List<NivoPieAbstractData> getNivoPieData(List<String> validities, List<String> sellTypes, List<String> months, List<String> year) {

        validities = verifyValidityList(validities);
        sellTypes = verifySellTypeList(sellTypes);
        months = verifyMonthsList(months);

        DataSumDTO pieData = repository.getNivoPieData(validities, sellTypes, months, verifyYears(year));
        List<NivoPieAbstractData> outputData = new ArrayList<>();
        outputData.add(new NivoPieAdultData(pieData.getAdults()));
        outputData.add(new NivoPieStudentData(pieData.getStudents()));
        outputData.add(new NivoPieSeniorData(pieData.getSeniors()));
        outputData.add(new NivoPieJuniorData(pieData.getJuniors()));
        outputData.add(new NivoPiePortableData(pieData.getPortable()));
        return outputData;
    }

    public List<NivoLineAbstractData> getJizdenyLineData(List<Boolean> discounted, List<String> months) {
        List<NivoLineAbstractData> personList = new ArrayList<>();

        discounted = verifyDiscountedList(discounted);
        months = verifyMonthsList(months);

        personList.add(new NivoGeneralLineData(JizdenkyTypes.FIFTEEN_MINUTES.getValue(), jizdenkyRepository.getFifteenMinutes(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.ONE_DAY.getValue(), jizdenkyRepository.getOneDay(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.ONE_DAY_ALL.getValue(), jizdenkyRepository.getOneDayAll(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.TWO_ZONES.getValue(), jizdenkyRepository.getTwoZones(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.THREE_ZONES.getValue(), jizdenkyRepository.getThreeZone(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.FOUR_ZONES.getValue(), jizdenkyRepository.getFourZone(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.FIVE_ZONES.getValue(), jizdenkyRepository.getFiveZone(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.SIX_ZONES.getValue(), jizdenkyRepository.getSixZone(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.SEVEN_ZONES.getValue(), jizdenkyRepository.getSevenZone(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.EIGHT_ZONES.getValue(), jizdenkyRepository.getEightZone(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.NINE_ZONES.getValue(), jizdenkyRepository.getNineZone(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.TEN_ZONES.getValue(), jizdenkyRepository.getTenZone(discounted, months)));
        personList.add(new NivoGeneralLineData(JizdenkyTypes.ELEVEN_ZONES.getValue(), jizdenkyRepository.getElevenZone(discounted, months)));
        return personList;
    }

    public List<NivoJizdenkyBarData> getJizdenkyBarData(List<Boolean> discounted, List<String> months) {
        discounted = verifyDiscountedList(discounted);
        months = verifyMonthsList(months);

        return jizdenkyRepository.getJizdenkyBarData(discounted, months);
    }

    public List<NivoPieAbstractData> getJizdenkyPieData(List<Boolean> discounted, List<String> months) {
        discounted = verifyDiscountedList(discounted);
        months = verifyMonthsList(months);

        DataSumJizdenkyDTO pieData = jizdenkyRepository.getJizdenkyPieData(discounted, months);
        List<NivoPieAbstractData> outputData = new ArrayList<>();
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.FIFTEEN_MINUTES.getValue(), pieData.getFifteenMinutes()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.ONE_DAY.getValue(), pieData.getOneDay()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.ONE_DAY_ALL.getValue(), pieData.getOneDayAll()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.TWO_ZONES.getValue(), pieData.getTwoZones()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.THREE_ZONES.getValue(), pieData.getThreeZones()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.FOUR_ZONES.getValue(), pieData.getFourZones()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.FIVE_ZONES.getValue(), pieData.getFiveZones()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.SIX_ZONES.getValue(), pieData.getSixZones()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.SEVEN_ZONES.getValue(), pieData.getSevenZones()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.EIGHT_ZONES.getValue(), pieData.getEightZones()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.NINE_ZONES.getValue(), pieData.getNineZones()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.TEN_ZONES.getValue(), pieData.getTenZones()));
        outputData.add(new NivoGeneralPieData(JizdenkyTypes.ELEVEN_ZONES.getValue(), pieData.getElevenZones()));
        return outputData;
    }
}
