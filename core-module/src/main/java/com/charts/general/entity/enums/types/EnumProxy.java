package com.charts.general.entity.enums.types;

import com.charts.general.entity.enums.IEnum;
import lombok.Data;

@Data
public class EnumProxy implements IEnum {

    private String value;
    private Integer orderValue;

    public EnumProxy(IEnum value) {
        this.value = value.getValue();
        this.orderValue = value.getOrderValue();
    }

    public EnumProxy(Integer value) {
        this.value = value.toString();
        this.orderValue = value;
    }

    public EnumProxy(String value, Integer orderValue) {
        this.value = value;
        this.orderValue = orderValue;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Integer getOrderValue() {
        return orderValue;
    }

}
