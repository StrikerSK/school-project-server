package com.javapid.service;

import com.javapid.entity.enums.Months;
import com.javapid.entity.enums.SellType;
import com.javapid.entity.enums.Validity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Validators {

    static List<String> verifyValidityList(List<String> validities) {
        if (validities == null) {
            validities = Stream.of(Validity.values()).map(Validity::getValue).collect(Collectors.toList());
        }
        return validities;
    }

    static List<String> verifySellTypeList(List<String> sellTypes) {
        if (sellTypes == null) {
            sellTypes = Stream.of(SellType.values()).map(SellType::getValue).collect(Collectors.toList());
        }
        return sellTypes;
    }

    static List<String> verifyMonthsList(List<String> months) {
        if (months == null) {
            months = Stream.of(Months.values()).map(Months::getValue).collect(Collectors.toList());
        }
        return months;
    }
}
