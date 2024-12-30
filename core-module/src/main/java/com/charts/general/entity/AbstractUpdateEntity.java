package com.charts.general.entity;

import com.charts.general.entity.enums.types.Months;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class AbstractUpdateEntity {

    @Column(name = "month")
    private Months month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "value")
    private Integer value;

}
