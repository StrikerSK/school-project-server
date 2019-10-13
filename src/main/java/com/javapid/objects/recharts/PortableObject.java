package com.javapid.objects.recharts;

public class PortableObject extends PersonAbstractClass {

    public PortableObject(String month, Long ticketCount){
        setName("Portable");
        setMonth(month);
        setValue(ticketCount);
    }
}
