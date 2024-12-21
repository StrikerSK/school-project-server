package com.charts.general;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.SellType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class EnumerationUtilTest {

    @Test
    public void testFromValue_ValidLabel() {
        Class<SellType> enumClass = SellType.class;
        String label = "Čipová karta";

        Optional<SellType> result = EnumUtils.getValue(enumClass, label);

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get(), SellType.CARD);
    }

    @Test
    public void testFromValue_InvalidLabel() {
        // Arrange
        Class<SellType> enumClass = SellType.class;
        String label = "Invalid Label";

        // Act and Assert
        Assert.assertThrows(IllegalArgumentException.class, () -> EnumUtils.fromValue(enumClass, label));
    }

    @Test
    public void testFromValue_NullEnumClass() {
        // Arrange
        Class<SellType> enumClass = null;
        String label = "Čipová karta";

        // Act and Assert
        Assert.assertThrows(NullPointerException.class, () -> EnumUtils.fromValue(enumClass, label));
    }

    @Test
    public void testFromValue_NullLabel() {
        // Arrange
        Class<SellType> enumClass = SellType.class;
        String label = null;

        // Act and Assert
        Assert.assertThrows(IllegalArgumentException.class, () -> EnumUtils.fromValue(enumClass, label));
    }
}