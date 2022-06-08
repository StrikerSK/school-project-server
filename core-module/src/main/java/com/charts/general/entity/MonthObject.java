package com.charts.general.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.charts.general.constants.Months.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthObject {

    @JsonProperty(JANUARY_VALUE)
    private Object January;

    @JsonProperty(FEBRUARY_VALUE)
    private Object February;

    @JsonProperty(MARCH_VALUE)
    private Object March;

    @JsonProperty(APRIL_VALUE)
    private Object April;

    @JsonProperty(MAY_VALUE)
    private Object May;

    @JsonProperty(JUNE_VALUE)
    private Object June;

    @JsonProperty(JULY_VALUE)
    private Object July;

    @JsonProperty(AUGUST_VALUE)
    private Object August;

    @JsonProperty(SEPTEMBER_VALUE)
    private Object September;

    @JsonProperty(OCTOBER_VALUE)
    private Object October;

    @JsonProperty(NOVEMBER_VALUE)
    private Object November;

    @JsonProperty(DECEMBER_VALUE)
    private Object December;

}
