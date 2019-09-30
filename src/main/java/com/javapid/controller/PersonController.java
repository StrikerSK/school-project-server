package com.javapid.controller;

import com.javapid.object.TestObject;
import com.javapid.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nivo")
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(name = "/getPerson")
	public List<TestObject> getPerson() {
		return personService.getPersonObject();
	}


}
