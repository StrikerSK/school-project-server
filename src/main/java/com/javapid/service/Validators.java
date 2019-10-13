package com.javapid.service;

import com.javapid.entity.enums.SellType;
import com.javapid.entity.enums.Validity;

import java.util.Arrays;
import java.util.List;

class Validators {

    static List<String> verifyValidityList(List<String> validities) {
        if (validities == null) {
            validities = Arrays.asList(Validity.MONTHLY.getValue(), Validity.FIVE_MONTHS.getValue(), Validity.THREE_MONTHS.getValue(), Validity.YEARLY.getValue());
        }
        return validities;
    }

    static List<String> verifySellTypeList(List<String> sellTypes) {
        if (sellTypes == null) {
            sellTypes = Arrays.asList(SellType.CARD.getValue(), SellType.COUPON.getValue());
        }
        return sellTypes;
    }

}
