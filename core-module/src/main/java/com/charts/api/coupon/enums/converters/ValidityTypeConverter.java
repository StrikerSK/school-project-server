package com.charts.api.coupon.enums.converters;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.api.coupon.enums.types.Validity;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
        return EnumUtils.getValue(Validity.class, validity).orElseThrow(IllegalArgumentException::new);
    }

}
