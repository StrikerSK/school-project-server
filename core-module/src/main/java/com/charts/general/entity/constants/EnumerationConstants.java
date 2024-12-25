package com.charts.general.entity.constants;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.types.Months;

import java.util.List;

public class EnumerationConstants {

    // Month value constants
    public static final String JANUARY_VALUE = "Január";
    public static final String FEBRUARY_VALUE = "Február";
    public static final String MARCH_VALUE = "Marec";
    public static final String APRIL_VALUE = "Apríl";
    public static final String MAY_VALUE = "Máj";
    public static final String JUNE_VALUE = "Jún";
    public static final String JULY_VALUE = "Júl";
    public static final String AUGUST_VALUE = "August";
    public static final String SEPTEMBER_VALUE = "September";
    public static final String OCTOBER_VALUE = "Október";
    public static final String NOVEMBER_VALUE = "November";
    public static final String DECEMBER_VALUE = "December";

    // Month lists
    public static final List<Months> MONTHS_LIST = EnumUtils.getValueList(Months.class);
    public static final List<String> MONTHS_VALUES = EnumUtils.getStringValues(Months.class);

}
