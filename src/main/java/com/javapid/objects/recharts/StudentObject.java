package com.javapid.objects.recharts;

public class StudentObject extends PersonAbstractClass {

    public StudentObject(String month, Integer ticketCount){
        setName("Student");
        setMonth(month);
        setValue(ticketCount);
    }
}
