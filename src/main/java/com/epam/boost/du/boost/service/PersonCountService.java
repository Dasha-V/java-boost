package com.epam.boost.du.boost.service;

import com.epam.boost.du.boost.entity.Person;

import java.util.List;


public interface PersonCountService
{
	int countPersonsByLastName(char startWith, List<Person> persons);

	double countAverageAge(List<Person> persons);

	List<Integer> findMissingIds(List<Person> persons);
}
