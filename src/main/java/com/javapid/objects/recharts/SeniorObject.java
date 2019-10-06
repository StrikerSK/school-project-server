package com.javapid.objects.recharts;

public class SeniorObject extends PersonAbstractClass {

    public SeniorObject(String month, Integer ticketCount){
        setName("Senior");
        setMonth(month);
        setValue(ticketCount);
    }
}
