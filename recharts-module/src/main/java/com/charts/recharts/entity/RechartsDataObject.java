package com.charts.recharts.entity;

import lombok.Data;

@Data
public class RechartsDataObject {
    private String name;
    private String month;
    private Long value;
    public RechartsDataObject(String name, String month, Long value) {
        this.name = name;
        this.month = month;
        this.value = value;
    }
}
