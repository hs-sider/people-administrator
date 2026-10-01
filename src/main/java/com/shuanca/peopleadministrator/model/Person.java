package com.shuanca.peopleadministrator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Person {

    private Integer id;

    @NotBlank(message = "Person name cannot be null")
    private String name;

    @NotNull(message = "Person age cannot be null")
    @Min(message = "Person age must be positive or zero", value = 0)
    private Integer age;

    private String address;

    public Person() {
    }

    public Person(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
