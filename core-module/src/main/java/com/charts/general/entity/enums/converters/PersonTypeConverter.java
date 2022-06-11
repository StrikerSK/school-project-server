package com.charts.general.entity.enums.converters;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.PersonType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.charts.general.entity.enums.EnumTypes.PERSON_TYPE_ENUM;

/**
 * To make sure that enum type is converted to database value
 */
@Converter(autoApply = true)
public class PersonTypeConverter implements AttributeConverter<PersonType, String> {

    @Override
    public String convertToDatabaseColumn(PersonType personType) {
        if (personType == null) {
            return null;
        };

        return personType.getValue();
    }

    @Override
    public PersonType convertToEntityAttribute(String personType) {
        return (PersonType) EnumUtils.getValue(PERSON_TYPE_ENUM, personType).orElseThrow(IllegalArgumentException::new);
    }

}
