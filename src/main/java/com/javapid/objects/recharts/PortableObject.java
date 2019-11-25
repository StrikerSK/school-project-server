package com.javapid.objects.recharts;

import com.javapid.entity.enums.PersonType;

public class PortableObject extends PersonAbstractClass {

    public PortableObject(String month, Long ticketCount){
        super(PersonType.PORTABLE.value, month, ticketCount);
    }
}
