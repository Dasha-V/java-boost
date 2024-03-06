package com.epam.boost.du.boost.service.impl;

import com.epam.boost.du.boost.entity.Person;
import com.epam.boost.du.boost.service.PersonCountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class PersonCountServiceImpl implements PersonCountService
{
	@Override
	public int countPersonsByLastName(char startWith, @NonNull List<Person> persons)
	{
		return persons.stream()
				.filter(Objects::nonNull)
				.filter(person -> !StringUtils.isBlank(person.getLastName()))
				.map(person -> person.getLastName())
				.filter(s -> s.startsWith(String.valueOf(startWith)))
				.collect(Collectors.toList()).size();

	}

	@Override
	public double countAverageAge(@NonNull List<Person> persons)
	{
		return persons.stream()
				.filter(Objects::nonNull)
				.mapToDouble(p -> p.getAge())
				.average().orElse(NumberUtils.DOUBLE_ZERO);

	}

	@Override
	public List<Integer> findMissingIds(@NonNull List<Person> persons)
	{
		var source = persons.
				stream()
				.filter(Objects::nonNull)
				.mapToInt(p -> p.getId())
				.boxed()
				.collect(Collectors.toList());

		var max = Collections.max(source);
		var min = NumberUtils.INTEGER_ONE;

		var target = IntStream.range(min, max + NumberUtils.INTEGER_ONE).boxed().collect(Collectors.toList());
		target.removeAll(source);

		return target;

	}
}
