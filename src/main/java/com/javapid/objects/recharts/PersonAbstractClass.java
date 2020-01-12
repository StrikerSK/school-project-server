package com.javapid.objects.recharts;

public class PersonAbstractClass {

    private String name;
    private String month;
    private Long value;

    public PersonAbstractClass(String name, String month, Long value) {
        this.name = name;
        this.month = month;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
