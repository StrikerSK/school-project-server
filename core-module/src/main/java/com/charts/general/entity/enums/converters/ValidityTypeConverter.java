package com.charts.general.entity.enums.converters;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.Validity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ValidityTypeConverter implements AttributeConverter<Validity, String> {

    @Override
    public String convertToDatabaseColumn(Validity validityType) {
        if (validityType == null) {
            return null;
        }

        return validityType.getValue();
    }

    @Override
    public Validity convertToEntityAttribute(String validity) {
        return EnumUtils.getEnumValues(Validity.class, validity).orElseThrow(IllegalArgumentException::new);
    }

}
