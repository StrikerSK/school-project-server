package com.charts.general.entity.enums.types;

import com.charts.general.entity.enums.IEnum;
import lombok.Data;

@Data
public class EnumAdapter implements IEnum {

    private String value;
    private Integer orderValue;

    public EnumAdapter(Integer value) {
        this.value = value.toString();
        this.orderValue = value;
    }

    public EnumAdapter(String value, Integer orderValue) {
        this.value = value;
        this.orderValue = orderValue;
    }

    public EnumAdapter(Boolean value) {
        this.value = value.toString();
        this.orderValue = value ? 1 : 0;
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
