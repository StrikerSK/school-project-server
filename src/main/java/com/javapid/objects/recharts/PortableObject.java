package com.javapid.objects.recharts;

public class PortableObject extends PersonAbstractClass {

    public PortableObject(String month, Integer ticketCount){
        setName("Portable");
        setMonth(month);
        setValue(ticketCount);
    }
}
