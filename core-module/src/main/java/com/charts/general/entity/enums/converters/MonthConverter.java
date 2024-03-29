package com.charts.general.entity.enums.converters;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.Months;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MonthConverter implements AttributeConverter<Months, String> {

    @Override
    public String convertToDatabaseColumn(Months months) {
        if (months == null) {
            return null;
        }

        return months.getValue();
    }

    @Override
    public Months convertToEntityAttribute(String personType) {
        return EnumUtils.getEnumValues(Months.class, personType).orElseThrow(IllegalArgumentException::new);
    }

}
