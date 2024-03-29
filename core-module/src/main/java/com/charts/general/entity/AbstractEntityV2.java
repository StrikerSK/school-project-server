package com.charts.general.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractEntityV2 extends AbstractEntity{

    @Column(name = "hodnota")
    private Integer value;

}
