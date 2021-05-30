package com.charts.general.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
	private Long id;

	@Column(name = "kod")
	@JsonProperty("kod")
	private String code;

	@Column(name = "mesiac")
	private String month;

	@Column(name = "rok")
	private Integer year;

	public AbstractDataEntity(String code, String month, Integer year) {
		this.code = code;
		this.month = month;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
