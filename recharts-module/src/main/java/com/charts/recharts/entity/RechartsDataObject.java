package com.charts.recharts.entity;

import com.charts.general.entity.enums.IEnum;
import com.charts.general.entity.enums.Months;
import lombok.Data;

import java.time.Month;

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

    public RechartsDataObject(IEnum name, Months month, Integer value) {
        this.name = name.getValue();
        this.month = month.getValue();
        this.value = value;
    }

}
