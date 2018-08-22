package com.rk.couchbase.sample.springbootcouchbase.entity;

import com.couchbase.client.java.repository.annotation.Field;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;


@Document
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationStrategy.)
    //default strategy = GenerationStrategy.USE_ATTRIBUTES, delimiter = "."
    private String id;

    @NotNull
    @Field
    private String userId;

    @Field
    private String firstName;

    @Field
    private String lastName;

    @Field
    private String email;

    @Field
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address){
        addresses.add(address);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
