package com.charts.general.utils;

import com.charts.general.exception.InvalidParameterException;

public abstract class AbstractFunctionUtils {

    public static final String MONTH_GROUP = "month";
    public static final String YEAR_GROUP = "year";

    public static void validateGroups(String upperGroup, String lowerGroup) {
        if (upperGroup.equals(lowerGroup)) {
            throw new InvalidParameterException("Cannot use same groups");
        }
    }

}
