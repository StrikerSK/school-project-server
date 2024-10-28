package com.charts.general.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractUpdateEntity extends AbstractEntity{

    @Column(name = "hodnota")
    private Integer value;

}
