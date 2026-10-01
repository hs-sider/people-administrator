package com.shuanca.peopleadministrator.controller;

import com.shuanca.peopleadministrator.model.Person;
import com.shuanca.peopleadministrator.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @Autowired
    PersonServiceImpl service;

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return new ResponseEntity<>(service.create(person), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<Person>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody Person person) {
        return ResponseEntity.ok(service.update(id, person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

