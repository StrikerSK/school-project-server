package com.charts.general.entity.enums.converters;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.SellType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.charts.general.entity.enums.EnumTypes.SELL_TYPE_ENUM;

@Converter(autoApply = true)
public class SellTypeConverter implements AttributeConverter<SellType, String> {

    @Override
    public String convertToDatabaseColumn(SellType sellType) {
        if (sellType == null) {
            return null;
        };

        return sellType.getValue();
    }

    @Override
    public SellType convertToEntityAttribute(String sellType) {
        return (SellType) EnumUtils.getValue(SELL_TYPE_ENUM, sellType).orElseThrow(IllegalArgumentException::new);
    }

}
