package com.javapid.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "selling_type")
public class TypeOfSelling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "platnost")
	private String validity;

	@Column(name = "typ_predaja")
	private String type;

	@OneToMany(mappedBy = "sellingType")
	private Set<Person> persons;

	public TypeOfSelling() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
}
