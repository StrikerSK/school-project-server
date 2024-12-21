package com.charts.general.entity.enums;

import com.charts.general.constants.EnumerationCouponConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

public class EnumUtilTest {

    @Test
    public void testFromValue_ValidLabel() {
        Class<SellType> enumClass = SellType.class;
        String label = EnumerationCouponConstants.CHIP_CARD;
        Optional<SellType> result = EnumUtils.getValue(enumClass, label);

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get(), SellType.CARD);
    }

    @Test
    public void testFromValue_InvalidLabel() {
        Class<SellType> enumClass = SellType.class;
        String label = "Invalid Label";
        Assert.assertThrows(IllegalArgumentException.class, () -> EnumUtils.fromValue(enumClass, label));
    }

    @Test
    public void testFromValue_NullEnumClass() {
        String label = EnumerationCouponConstants.CHIP_CARD;
        Assert.assertThrows(NullPointerException.class, () -> EnumUtils.fromValue(null, label));
    }

    @Test
    public void testFromValue_NullLabel() {
        Class<SellType> enumClass = SellType.class;
        Assert.assertThrows(IllegalArgumentException.class, () -> EnumUtils.fromValue(enumClass, null));
    }

    @Test
    public void testGetValueList() {
        Class<SellType> enumClass = SellType.class;
        List<SellType> result = EnumUtils.getValueList(enumClass);
        Assert.assertEquals(result.size(), SellType.values().length);

        SellType sellType = result.get(0);
        Assert.assertEquals(sellType, SellType.CARD);
        Assert.assertEquals(sellType.getOrderValue(), 1);
    }

}