package com.charts.general.utils;

import com.charts.general.exception.InvalidParameterException;
import org.apache.commons.lang3.StringUtils;

public abstract class AbstractFunctionUtils {

    public static final String MONTH_GROUP = "month";
    public static final String YEAR_GROUP = "year";

    public static void validateGroups(String upperGroup, String lowerGroup) {
        if (StringUtils.isEmpty(upperGroup) || StringUtils.isEmpty(lowerGroup)) {
            throw new InvalidParameterException("Groups cannot be null nor empty");
        }

        if (upperGroup.equals(lowerGroup)) {
            throw new InvalidParameterException("Cannot use same groups");
        }
    }

}
