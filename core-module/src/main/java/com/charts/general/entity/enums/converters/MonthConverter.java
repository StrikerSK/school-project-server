package com.charts.general.entity.enums.converters;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.types.Months;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
    public Months convertToEntityAttribute(String month) {
        return EnumUtils.getValue(Months.class, month).orElseThrow(IllegalArgumentException::new);
    }

}
