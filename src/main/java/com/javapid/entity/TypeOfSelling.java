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

	@Column(name = "typ_predaja")
	private String type;

	@ManyToOne(fetch = FetchType.EAGER)
	private Validity validity;

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

	public Validity getValidity() {
		return validity;
	}

	public void setValidity(Validity validity) {
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
