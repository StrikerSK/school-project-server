package com.charts.nivo.entity;

import com.charts.general.entity.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NivoBubbleData {

    private String name;

    @JsonIgnore
    private Integer orderValue;

    @JsonInclude(Include.NON_NULL)
    private List<NivoBubbleData> children;

    @JsonInclude(Include.NON_NULL)
    private Integer value;

    public NivoBubbleData(String name, List<NivoBubbleData> children) {
        this.name = name;
        this.children = children;
    }

    public NivoBubbleData(IEnum name, List<NivoBubbleData> children) {
        this.name = name.getValue();
        this.orderValue = name.getOrderValue();
        this.children = children;
    }

    public NivoBubbleData(IEnum name, Integer value) {
        this.name = name.getValue();
        this.orderValue = name.getOrderValue();
        this.value = value;
    }

}
