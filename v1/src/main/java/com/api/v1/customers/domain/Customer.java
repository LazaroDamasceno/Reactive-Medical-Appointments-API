package com.api.v1.customers.domain;

import com.api.v1.people.domain.Person;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.UUID;

@Document
@Getter
public class Customer {

    @Id
    private final UUID id;
    private Person person;
    private final String createdAt;

    private Customer(Person person) {
        this.person = person;
        this.id = UUID.randomUUID();
        this.createdAt = ZonedDateTime.now().toString();
    }

    public static Customer create(Person person) {
        return new Customer(person);
    }

}
