package com.shuanca.peopleadministrator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuanca.peopleadministrator.model.Person;
import com.shuanca.peopleadministrator.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonServiceImpl personServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenPostPerson_thenCreatePersonAndReturn201() throws Exception {
        Person person = new Person(1000, "James", 22, "5th avenue");
        Mockito.when(personServiceImpl.create(Mockito.any(Person.class))).thenReturn(person);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1000))
                .andExpect(jsonPath("$.name").value("James"));
    }

    @Test
    void whenGetAllPerson_thenReturn200() throws Exception {
        Person person1 = new Person(1000, "James", 33, "5th avenue");
        Person person2 = new Person(1001, "John", 44, "6th avenue");
        Mockito.when(personServiceImpl.getAll()).thenReturn(List.of(person1, person2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/people")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void whenGetPersonById_thenReturn200() throws Exception {
        Person person1 = new Person(1000, "James", 33, "5th avenue");
        Mockito.when(personServiceImpl.getById(Mockito.any(Integer.class))).thenReturn(person1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/people/1000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1000))
                .andExpect(jsonPath("$.name").value("James"));
    }

    @Test
    void whenUpdatePersonById_thenReturn200() throws Exception {
        Person person = new Person(1000, "James", 33, "5th avenue");
        Mockito.when(personServiceImpl.update(Mockito.any(Integer.class), Mockito.any(Person.class))).thenReturn(person);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/people/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1000))
                .andExpect(jsonPath("$.name").value("James"));
    }

    @Test
    void whenDeletePersonById_thenReturn200() throws Exception {
        Person person = new Person(1000, "James", 33, "5th avenue");
        Mockito.when(personServiceImpl.delete(Mockito.any(Integer.class))).thenReturn(person);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/people/1000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1000))
                .andExpect(jsonPath("$.name").value("James"));
    }
}