package com.epam.boost.du.boost.repository;

import com.epam.boost.du.boost.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Person, Integer>
{
}
