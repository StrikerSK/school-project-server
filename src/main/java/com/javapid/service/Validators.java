package com.javapid.service;

import com.javapid.entity.enums.Months;
import com.javapid.entity.enums.SellType;
import com.javapid.entity.enums.Validity;
import com.javapid.entity.enums.ValueGetter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Validators {

    static List<String> verifyValidityList(List<String> validities) {
        return verifyList(validities, getEnumList(Validity.values()));
    }

    static List<String> verifySellTypeList(List<String> sellTypes) {
        return verifyList(sellTypes, getEnumList(SellType.values()));
    }

    static List<String> verifyMonthsList(List<String> months) {
        return verifyList(months, getEnumList(Months.values()));
    }

    static List<Boolean> verifyDiscountedList(List<Boolean> discounted) {
        return verifyList(discounted, Arrays.asList(true, false));
    };

    private static <T> List<T> verifyList(List<T> checkedArray, List<T> enumList){
        try {
            checkedArray = checkedArray.stream().filter(enumList::contains).collect(Collectors.toList());
            if(checkedArray.size() == 0){
                checkedArray = enumList;
            }
            return checkedArray;
        } catch (NullPointerException e){
            return enumList;
        }
    }

    private static <T extends ValueGetter> List<String> getEnumList(T[] enumValues){
        return Arrays.stream(enumValues).map(T::getValue).collect(Collectors.toList());
    };
}
