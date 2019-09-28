package com.javapid.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dates")
public class TimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "kod")
	@JsonProperty("kod")
	private String code;

	@Column(name = "mesiac")
	private String month;

	@Column(name = "rok")
	private Integer year;

	@OneToMany(mappedBy = "timeEntities")
	private Set<Person> personEntities;

	public TimeEntity() {
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

	public Set<Person> getPersonEntities() {
		return personEntities;
	}

	public void setPersonEntities(Set<Person> personEntities) {
		this.personEntities = personEntities;
	}
}
