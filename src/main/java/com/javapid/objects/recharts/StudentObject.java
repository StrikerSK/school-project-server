package com.javapid.objects.recharts;

import com.javapid.entity.enums.PersonType;

public class StudentObject extends PersonAbstractClass {

    public StudentObject(String month, Long ticketCount){
        super(PersonType.STUDENT.value, month, ticketCount);
    }
}
