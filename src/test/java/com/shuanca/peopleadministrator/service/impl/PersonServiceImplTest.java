package com.shuanca.peopleadministrator.service.impl;

import com.shuanca.peopleadministrator.exception.custom.PersonNotFoundException;
import com.shuanca.peopleadministrator.model.Person;
import com.shuanca.peopleadministrator.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonServiceImpl personServiceImpl;

    @Test
    void whenCreate_thenSaveAndReturnPerson() {
        Person person = new Person(1, "James", 33, "5th avenue");
        when(personRepository.save(person)).thenReturn(person);

        Person personCreated = personServiceImpl.create(person);

        assertNotNull(personCreated);
        assertEquals(1, personCreated.getId());
        assertEquals("James", personCreated.getName());
        assertEquals(33, personCreated.getAge());
        assertEquals("5th avenue", personCreated.getAddress());

        verify(personRepository, times(1)).save(person); // verify repository interaction
    }

    @Test
    void whenGetAllPerson_thenReturnAllPerson() {
        // Arrange
        Person person1 = new Person(1, "James", 33, "5th avenue");
        Person person2 = new Person(2, "John", 44, "Main street");
        when(personRepository.getAll()).thenReturn(List.of(person1, person2));

        Collection<Person> personCollection = personServiceImpl.getAll();

        assertEquals(2, personCollection.size());
        verify(personRepository, times(1)).getAll();
    }

    @Test
    void whenGetPersonById_thenReturnPersonIfExists() {

        Person person = new Person(1, "James", 33, "5th avenue");
        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        Person personFound = personServiceImpl.getById(1);

        assertNotNull(personFound);
        assertEquals(1, personFound.getId());
        verify(personRepository, times(1)).findById(1);
    }

    @Test
    void whenUpdatePerson_thenUpdateAndReturnUpdatedPerson() {

        Person personExisting = new Person(1, "James", 33, "5th avenue");
        Person personUpdated = new Person(1, "John", 44, "8th avenue");

        when(personRepository.update(1, personExisting)).thenReturn(personUpdated);
        when(personRepository.existById(1)).thenReturn(true);

        Person personResult = personServiceImpl.update(1, personExisting);

        assertNotNull(personResult);
        assertEquals("John", personResult.getName());
        verify(personRepository, times(1)).update(1, personExisting);
    }

    @Test
    void whenDeletePerson_thenCallRepositoryDeleteById() {

        Person person = new Person(1, "James", 33, "5th avenue");
        when(personRepository.deleteById(1)).thenReturn(person);
        when(personRepository.existById(1)).thenReturn(true);

        Person personResult = personServiceImpl.delete(1);

        assertNotNull(personResult);
        assertEquals("James", personResult.getName());
        verify(personRepository, times(1)).deleteById(1);
    }

    @Test
    void whenGetPersonByNonExistentId_thenThrowExceptionWhenPersonNotFound(){

        when(personRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> {
            personServiceImpl.getById(99);
        });
    }

    @Test
    void whenDeletePersonByNonExistentId_thenThrowExceptionWhenPersonNotFound(){

        when(personRepository.existById(99)).thenReturn(false);

        assertThrows(PersonNotFoundException.class, () -> {
            personServiceImpl.delete(99);
        });
    }

    @Test
    void whenUpdatePersonByNonExistentId_thenThrowExceptionWhenPersonNotFound(){

        Person personUpdated = new Person(1, "John", 44, "8th avenue");
        when(personRepository.existById(99)).thenReturn(false);

        assertThrows(PersonNotFoundException.class, () -> {
            personServiceImpl.update(99, personUpdated);
        });
    }
}