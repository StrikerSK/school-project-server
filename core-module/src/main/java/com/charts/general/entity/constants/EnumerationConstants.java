package com.charts.general.entity.constants;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.types.EnumAdapter;
import com.charts.general.entity.enums.types.Months;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public static final List<Months> MONTH_LIST = EnumUtils.getValueList(Months.class);
    public static final List<String> MONTH_VALUES = EnumUtils.getStringValues(Months.class);

    // Year lists
    public static final List<Integer> YEAR_LIST = Arrays.asList(2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025);
    public static final List<EnumAdapter> YEAR_ENUMS = YEAR_LIST.stream().map(EnumAdapter::new).collect(Collectors.toList());

}
