package com.charts.api.coupon.constants;

import com.charts.general.entity.enums.types.Months;
import com.charts.api.coupon.enums.types.PersonType;
import com.charts.api.coupon.enums.types.SellType;
import com.charts.api.coupon.enums.types.Validity;
import com.charts.general.entity.enums.*;

import java.util.List;

public class EnumerationCouponConstants {

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

    // PersonType value constants
    public static final String ADULT_VALUE = "Dospelý";
    public static final String SENIOR_VALUE = "Dôchodcovia";
    public static final String JUNIOR_VALUE = "Juniori";
    public static final String PORTABLE_VALUE = "Prenosná";
    public static final String STUDENT_VALUE = "Študenti";
    public static final String CHILDREN_VALUE = "Deti";

    // PersonType lists
    public static final List<PersonType> PERSON_TYPE_LIST = EnumUtils.getValueList(PersonType.class);
    public static final List<String> PERSON_TYPE_VALUES = EnumUtils.getStringValues(PersonType.class);

    // SellType value constants
    public static final String CHIP_CARD =  "Čipová karta";
    public static final String PAPER_COUPON =  "Papierový kupón";
    public static final String E_SHOP =  "EShop";

    // SellType lists
    public static final List<SellType> SELL_TYPE_LIST = EnumUtils.getValueList(SellType.class);
    public static final List<String> SELL_TYPE_VALUES = EnumUtils.getStringValues(SellType.class);

    // Validity value constants
    public static final String ONE_MONTH =  "Mesačná";
    public static final String THREE_MONTH =  "3 Mesačná";
    public static final String FIVE_MONTH =  "5 Mesačná";
    public static final String ONE_YEAR =  "Ročná";

    // Validity lists
    public static final List<Validity> VALIDITY_LIST = EnumUtils.getValueList(Validity.class);
    public static final List<String> VALIDITY_VALUES = EnumUtils.getStringValues(Validity.class);

}
