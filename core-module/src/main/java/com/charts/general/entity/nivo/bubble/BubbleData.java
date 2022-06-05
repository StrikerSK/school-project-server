package com.charts.general.entity.nivo.bubble;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class BubbleData {

    private String name;

    @JsonInclude(Include.NON_NULL)
    private List<BubbleData> children;

    @JsonInclude(Include.NON_NULL)
    private Integer value;

    public BubbleData(String name, List<BubbleData> children) {
        this.name = name;
        this.children = children;
    }

    public BubbleData(String name, BubbleData children) {
        this.name = name;
        this.children = Stream.of(children).collect(Collectors.toList());
    }

    public BubbleData(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

}
