package com.epam.boost.du.boost.service;

import com.epam.boost.du.boost.dto.PersonDTO;

import java.util.Optional;


public interface PersonService
{
	Optional<PersonDTO> getPerson(final String id);

	PersonDTO createPerson(final String firstName, final String lastName);

	void removeUser(final String id);

	PersonDTO updatePerson(final PersonDTO person);
}
