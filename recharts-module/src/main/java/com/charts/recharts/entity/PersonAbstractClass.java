package com.charts.recharts.entity;

import lombok.Data;

@Data
public class PersonAbstractClass {
    private String name;
    private String month;
    private Long value;
    public PersonAbstractClass(String name, String month, Long value) {
        this.name = name;
        this.month = month;
        this.value = value;
    }
}
