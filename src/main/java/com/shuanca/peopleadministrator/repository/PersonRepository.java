package com.shuanca.peopleadministrator.repository;

import com.shuanca.peopleadministrator.exception.custom.PersonNotFoundException;
import com.shuanca.peopleadministrator.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PersonRepository {

    private static final Logger logger = LoggerFactory.getLogger(PersonRepository.class);

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    private final Map<Integer, Person> personMap = new HashMap<>();

    public Collection<Person> getAll() {
        logger.info("Processing getAll at repository layer");
        return personMap.values();
    }

    public boolean existById(Integer id) {
        return personMap.containsKey(id);
    }

    public Optional<Person> findById(Integer id) {
        logger.info("Processing findById at repository layer");

        if(personMap.containsKey(id)) {
            return Optional.of(personMap.get(id));
        }
        return Optional.empty();
    }

    public Person save(Person person) {
        logger.info("Processing save at repository layer");

        Person newPerson = new Person(
                ID_GENERATOR.getAndIncrement(),
                person.getName(),
                person.getAge(),
                person.getAddress());

        personMap.put(newPerson.getId(), newPerson);

        return newPerson;
    }

    public Person update(Integer id, Person updatedPerson) {
        logger.info("Processing update at repository layer");

        Person person = findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
        person.setAddress(updatedPerson.getAddress());

        return person;
    }

    public Person deleteById(int id) {
        logger.info("Processing delete at repository layer");

        Person person = findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        personMap.remove(person.getId());
        return person;
    }

}

