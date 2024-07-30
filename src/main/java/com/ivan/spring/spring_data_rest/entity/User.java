package com.ivan.spring.spring_data_rest.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class User {


    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("age")
    private byte age;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("name")
    private String name;


    public User() {
    }

    public User(Long id, Byte age, String lastName, String name) {
        this.id = id;
        this.age = age;
        this.lastName = lastName;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}