package com.charts.recharts.entity;

import lombok.Data;

@Data
public class RechartsDataObject {
    private String name;
    private String month;
    private Integer value;
    public RechartsDataObject(String name, String month, Integer value) {
        this.name = name;
        this.month = month;
        this.value = value;
    }
}
