package com.javapid.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "validity")
public class Validity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "platnost")
	private String validity;

	@OneToMany(mappedBy = "validity")
	private Set<TypeOfSelling> typeOfSellings;

}
