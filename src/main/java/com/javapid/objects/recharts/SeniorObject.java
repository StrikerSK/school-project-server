package com.javapid.objects.recharts;

import com.javapid.entity.enums.PersonType;

public class SeniorObject extends PersonAbstractClass {

    public SeniorObject(String month, Long ticketCount){
        super(PersonType.SENIOR.value, month, ticketCount);
    }
}
