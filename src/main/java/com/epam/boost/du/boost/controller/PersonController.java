package com.epam.boost.du.boost.controller;

import com.epam.boost.du.boost.dto.PersonDTO;
import com.epam.boost.du.boost.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RequestMapping(value = "api/v1/persons")
@RestController("Persons")
public class PersonController
{
	private final PersonService personService;

	public PersonController(@NonNull PersonService personService)
	{
		this.personService = personService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonDTO> getPerson(@PathVariable String id)
	{
		final var person = personService.getPerson(id);
		if (person.isPresent())
		{
			return ResponseEntity.ok(person.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<PersonDTO> updatePerson(@PathVariable String id,
			@RequestBody PersonDTO request)
	{
		final var person = personService.updatePerson(request);
		if (Objects.equals(id, request.getId()))
		{
			return ResponseEntity.ok(person);
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PersonDTO createPerson(@RequestBody PersonDTO request)
	{
		return personService.createPerson(request.getFirstName(), request.getLastName());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable String id)
	{
		personService.removeUser(id);
	}


}
