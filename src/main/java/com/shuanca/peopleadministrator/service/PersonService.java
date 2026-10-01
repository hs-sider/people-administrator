package com.shuanca.peopleadministrator.service;

import com.shuanca.peopleadministrator.model.Person;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;

@Validated
public interface PersonService {

    Person create(@Valid Person person);
    Collection<Person> getAll();
    Person getById(Integer id);
    Person update(Integer id, @Valid Person person);
    Person delete(Integer id);
}

