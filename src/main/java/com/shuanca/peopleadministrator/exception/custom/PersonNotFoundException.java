package com.shuanca.peopleadministrator.exception.custom;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(Integer id) {
        super("Person with id " + id + " was not found");
    }
}

