package com.epam.boost.du.boost.service.impl;

import com.epam.boost.du.boost.entity.Person;
import com.epam.boost.du.boost.service.PersonCountService;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;



class PersonCountServiceImplTest
{

	private Person ivanov;
	private Person petrov;
	private Person sidorov;
	private Person pavlov;

	private PersonCountService service = new PersonCountServiceImpl();

	@BeforeEach
	public void setUp()
	{
		ivanov = new Person();
		petrov = new Person();
		sidorov = new Person();
	}



	@Test
	void shouldCountPersonsByLastName()
	{
		ivanov.setLastName("Ivanov");
		petrov.setLastName("Petrov");
		sidorov.setLastName("Sidorov");

		//when
		char startWith = 'I';
		var count = service.countPersonsByLastName(startWith, List.of(ivanov, petrov, sidorov));
		//then
		Assert.assertEquals(1, count);
	}

	@Test
	void shouldCountAverageAge()
	{
		ivanov.setAge(NumberUtils.INTEGER_ONE);
		petrov.setAge(NumberUtils.INTEGER_TWO);
		sidorov.setAge(NumberUtils.INTEGER_TWO);

		var expected = (double) 5 / 3;
		//when
		var averageAge = service.countAverageAge(List.of(ivanov, petrov, sidorov));
		//then
		Assert.assertEquals(expected, averageAge, 0.001);
	}

	@Test
	void shouldFindMissingIds()
	{
		pavlov = new Person();

		petrov.setId(2);
		sidorov.setId(4);

		//when
		var expectedIds = List.of(1, 3);
		var missingIds = service.findMissingIds(List.of(ivanov, petrov, sidorov, pavlov));

		//then
		Assert.assertTrue(missingIds.containsAll(expectedIds));
	}
}
