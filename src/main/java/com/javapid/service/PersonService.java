package com.javapid.service;

import com.javapid.entity.Person;
import com.javapid.object.TestObject;
import com.javapid.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<TestObject> getPersonObject() {
		List<Person> personList = personRepository.findAll();
		return personList.stream().map(this::createPieData).collect(Collectors.toList());
	}

	private TestObject createPieData(Person person) {
		return new TestObject(
				person.getPersonName().getName(),
				person.getSellCount()
		);
	}

}
