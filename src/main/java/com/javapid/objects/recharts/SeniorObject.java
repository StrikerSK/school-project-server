package com.javapid.objects.recharts;

public class SeniorObject extends PersonAbstractClass {

    public SeniorObject(String month, Long ticketCount){
        setName("Senior");
        setMonth(month);
        setValue(ticketCount);
    }
}
