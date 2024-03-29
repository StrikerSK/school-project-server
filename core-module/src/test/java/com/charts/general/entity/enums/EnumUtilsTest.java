package com.charts.general.entity.enums;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class EnumUtilsTest {

    @Test
    public void TestEnums() {
        List<Months> enums = EnumUtils.getEnumValues(Months.class);
        Assert.assertEquals(enums, Arrays.asList(Months.values()));
    }

}
