package com.charts.nivo.entity;

import com.charts.general.entity.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class NivoBubbleData {

    private String name;

    @JsonInclude(Include.NON_NULL)
    private List<NivoBubbleData> children;

    @JsonInclude(Include.NON_NULL)
    private Integer value;

    public NivoBubbleData(String name, List<NivoBubbleData> children) {
        this.name = name;
        this.children = children;
    }

    public NivoBubbleData(String name, NivoBubbleData children) {
        this.name = name;
        this.children = Stream.of(children).collect(Collectors.toList());
    }

    public NivoBubbleData(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public NivoBubbleData(IEnum name, Integer value) {
        this.name = name.getValue();
        this.value = value;
    }

}
