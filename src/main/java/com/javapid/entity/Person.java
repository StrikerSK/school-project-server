package com.javapid.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
	private Long id;

	@Column(name = "pocet_predanych")
	private Integer sellCount;

	@ManyToOne(fetch = FetchType.EAGER)
	private PersonName personName;

	@ManyToOne(fetch = FetchType.EAGER)
	private TimeEntity timeEntities;

	@ManyToOne(fetch = FetchType.EAGER)
	private TypeOfSelling sellingType;

	public Person() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}

	public TimeEntity getTimeEntities() {
		return timeEntities;
	}

	public void setTimeEntities(TimeEntity timeEntities) {
		this.timeEntities = timeEntities;
	}

	public TypeOfSelling getSellingType() {
		return sellingType;
	}

	public void setSellingType(TypeOfSelling sellingType) {
		this.sellingType = sellingType;
	}

	public PersonName getPersonName() {
		return personName;
	}

	public void setPersonName(PersonName personName) {
		this.personName = personName;
	}
}
