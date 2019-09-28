package com.javapid.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person_name")
public class PersonName {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "meno")
	private String name;

	@OneToMany(mappedBy = "personName")
	private Set<Person> persons;

	public PersonName() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
}
