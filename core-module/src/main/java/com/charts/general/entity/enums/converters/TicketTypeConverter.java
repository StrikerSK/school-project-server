package com.charts.general.entity.enums.converters;

import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.TicketTypes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.charts.general.entity.enums.EnumTypes.TICKET_TYPE_ENUM;

@Converter(autoApply = true)
public class TicketTypeConverter implements AttributeConverter<TicketTypes, String> {

    @Override
    public String convertToDatabaseColumn(TicketTypes ticketType) {
        if (ticketType == null) {
            return null;
        };

        return ticketType.getValue();
    }

    @Override
    public TicketTypes convertToEntityAttribute(String personType) {
        return (TicketTypes) EnumUtils.getValue(TICKET_TYPE_ENUM, personType).orElseThrow(IllegalArgumentException::new);
    }

}
