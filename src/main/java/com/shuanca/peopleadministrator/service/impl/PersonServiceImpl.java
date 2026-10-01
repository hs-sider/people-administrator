package com.shuanca.peopleadministrator.service.impl;

import com.shuanca.peopleadministrator.exception.custom.PersonNotFoundException;
import com.shuanca.peopleadministrator.model.Person;
import com.shuanca.peopleadministrator.repository.PersonRepository;
import com.shuanca.peopleadministrator.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;

@Service
@Validated
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository repository;

    public Person create(@Valid Person person) {
        return repository.save(person);
    }

    public Collection<Person> getAll() {
        return repository.getAll();
    }

    public Person getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    public Person update(Integer id, @Valid Person updatedPerson) {
        if (!repository.existById(id)) {
            throw new PersonNotFoundException(id);
        }
        return repository.update(id, updatedPerson);
    }

    public Person delete(Integer id) {
        if (!repository.existById(id)) {
            throw new PersonNotFoundException(id);
        }
        return repository.deleteById(id);
    }
}

