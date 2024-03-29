package com.charts.general.entity.enums.converters;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.SellType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SellTypeConverter implements AttributeConverter<SellType, String> {

    @Override
    public String convertToDatabaseColumn(SellType sellType) {
        if (sellType == null) {
            return null;
        }

        return sellType.getValue();
    }

    @Override
    public SellType convertToEntityAttribute(String sellType) {
        return EnumUtils.getEnumValues(SellType.class, sellType).orElseThrow(IllegalArgumentException::new);
    }

}
