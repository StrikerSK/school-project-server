package com.charts.general.entity.enums;

import com.charts.api.coupon.enums.types.SellType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.charts.api.coupon.constants.EnumerationCouponConstants.CHIP_CARD;
import static com.charts.api.coupon.constants.EnumerationCouponConstants.E_SHOP;

public class EnumUtilTest {

    @Test
    public void testFromValue_ValidLabel() {
        Class<SellType> enumClass = SellType.class;
        Optional<SellType> result = EnumUtils.getValue(enumClass, CHIP_CARD);

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
        Assert.assertThrows(NullPointerException.class, () -> EnumUtils.fromValue(null, CHIP_CARD));
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

    @Test
    public void testGetStringValues() {
        List<String> enums = Arrays.asList(CHIP_CARD, E_SHOP);
        List<SellType> sellTypes = EnumUtils.getValueList(enums, SellType.class);

        Assert.assertEquals(sellTypes.size(), 2);
        Assert.assertTrue(sellTypes.contains(SellType.CARD));
        Assert.assertTrue(sellTypes.contains(SellType.ESHOP));
        Assert.assertFalse(sellTypes.contains(SellType.COUPON));
    }



}