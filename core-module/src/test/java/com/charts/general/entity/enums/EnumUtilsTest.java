package com.charts.general.entity.enums;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.charts.general.constants.ValidityConstants.MONTHLY_LABEL;
import static com.charts.general.constants.ValidityConstants.YEARLY_LABEL;

public class EnumUtilsTest {

    @Test
    public void TestEnums() {
        List<Months> enums = EnumUtils.getEnumValues(Months.class);
        Assert.assertEquals(enums, Arrays.asList(Months.values()));
    }

    @Test
    public void TestEnumsFiltering() {
        List<String> labels = new ArrayList<>();
        labels.add(MONTHLY_LABEL);
        labels.add(YEARLY_LABEL);

        List<String> enums = EnumUtils.findValue(Validity.class, labels).stream().map(IEnum::getValue).collect(Collectors.toList());
        Assert.assertEquals(enums.size(), 2);
        Assert.assertEquals(enums, labels);
    }

    @Test
    public void TestEnumsFiltering_NonExistingLabel() {
        List<String> labels = new ArrayList<>();
        labels.add(MONTHLY_LABEL);
        labels.add(YEARLY_LABEL);
        labels.add("Other");

        List<String> enums = EnumUtils.findValue(Validity.class, labels).stream().map(IEnum::getValue).collect(Collectors.toList());
        Assert.assertEquals(enums.size(), 2);
        Assert.assertListNotContains(enums, i -> i.equals("other"), "Value should not be present");
    }

}
