package com.shuanca.peopleadministrator.repository;

import com.shuanca.peopleadministrator.exception.custom.PersonNotFoundException;
import com.shuanca.peopleadministrator.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        Person person = new Person(null, "James", 33, "5th avenue");
        personRepository.save(person);
    }

    @Test
    void whenGetAll_thenReturnPerson() {

        Person person1 = new Person(null, "James", 33, "5th avenue");
        personRepository.save(person1);
        Person person2 = new Person(null, "John", 34, "6th avenue");
        personRepository.save(person2);
        Person person3 = new Person(null, "Jane", 35, "7th avenue");
        personRepository.save(person3);

        List<Person> personList = new ArrayList<>(personRepository.getAll());

        assertNotNull(personList);
        assertTrue(personList.size() >= 2);
    }

    @Test
    void whenFindById_thenReturnPerson() {

        Integer id = 1000;
        String name = "James";
        Integer age = 33;
        String address = "5th avenue";

        Person personSaved = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));

        assertNotNull(personSaved);
        assertThat(personSaved.getId()).isEqualTo(id);
        assertThat(personSaved.getName()).isEqualTo(name);
        assertThat(personSaved.getAge()).isEqualTo(age);
        assertThat(personSaved.getAddress()).isEqualTo(address);
    }

    @Test
    void whenUpdateById_thenReturnPerson() {

        String oldName = "James";
        Integer oldAge = 33;
        String oldAddress = "5th avenue";

        Person savedPerson = personRepository.save(new Person(null, oldName, oldAge, oldAddress));

        String newName = "John Doe";
        Integer newAge = 44;
        String newAddress = "Main Street";

        int sizeBeforeUpdate = personRepository.getAll().size();
        Person personUpdated = personRepository.update(savedPerson.getId(), new Person(null, newName, newAge, newAddress));
        int sizeAfterUpdate = personRepository.getAll().size();

        assertNotNull(personUpdated);
        assertEquals(sizeBeforeUpdate, sizeAfterUpdate);
        assertThat(personUpdated.getId()).isEqualTo(savedPerson.getId());
        assertThat(personUpdated.getName()).isEqualTo(newName);
        assertThat(personUpdated.getAge()).isEqualTo(newAge);
        assertThat(personUpdated.getAddress()).isEqualTo(newAddress);
    }

    @Test
    void whenDeleteById_thenReturnPerson() {

        Integer id = 1000;
        String name = "James";
        Integer age = 33;
        String address = "5th avenue";

        int sizeBeforeDelete = personRepository.getAll().size();
        Person personDeleted = personRepository.deleteById(id);
        int sizeAfterDelete = personRepository.getAll().size();

        assertNotNull(personDeleted);
        assertEquals(sizeBeforeDelete, sizeAfterDelete + 1);
        assertThat(personDeleted.getId()).isEqualTo(id);
        assertThat(personDeleted.getName()).isEqualTo(name);
        assertThat(personDeleted.getAge()).isEqualTo(age);
        assertThat(personDeleted.getAddress()).isEqualTo(address);
    }

}