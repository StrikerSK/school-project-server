package com.charts.general.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractUpdateEntity extends AbstractEntity{

    @Column(name = "hodnota")
    private Integer value;

}
