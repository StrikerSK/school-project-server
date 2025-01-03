package com.charts.general.entity;

import com.charts.general.entity.enums.types.Months;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public abstract class AbstractUpdateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "month")
    @CsvBindByName
    private Months month;

    @Column(name = "year")
    @CsvBindByName
    private Integer year;

    @Column(name = "value")
    @CsvBindByName
    private Integer value;

}
